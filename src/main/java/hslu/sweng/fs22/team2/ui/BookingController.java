package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.validation.ValidationSupport;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingController {
    public ComboBox<Screening> screeningPicker;
    public Button makeBookingButton;
    public ListView<Seat> seatsTable;
    ValidationSupport validationSupport = new ValidationSupport();
    Booking toEdit;
    private Management management;

    public void initialize() throws SQLException, ParseException {
        toEdit = new Booking();
        management = new Management();
        screeningPicker.setItems(FXCollections.observableList(management.getScreeningList()));
        seatsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        screeningPicker.setConverter(new StringConverter<Screening>() {
            @Override
            public String toString(Screening screening) {
                if (screening == null) return "Please select";
                LocalDateTime timeOfScreening = Helper.convertMillisToDateTime(screening.getScreeningTime());
                return ((Movie) management.searchMovieByID(screening.getMovieID())).getMovieName()
                        + " on " + timeOfScreening.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        + " at " + timeOfScreening.format(DateTimeFormatter.ofPattern("HH.mm"));
            }

            @Override
            public Screening fromString(String string) {
                return null;
            }
        });

        seatsTable.setCellFactory(lv -> new ListCell<Seat>() {
            @Override
            public void updateItem(Seat seat, boolean empty) {
                super.updateItem(seat, empty);
                setText(empty ? null : "Row: " + seat.getX() + ", Seat: " + seat.getY());
            }
        });
    }

    public void setToEdit(Booking booking) {
        toEdit = booking;
    }

    public void makeBooking(ActionEvent actionEvent) throws SQLException {
        //Appending all the selected seats to a string builder.
        StringBuilder seats = new StringBuilder();
        for (Seat seat : seatsTable.getSelectionModel().getSelectedItems())
            seats.append(seat.getSeatID()).append(";");
        Booking booking = new Booking("", screeningPicker.getValue().getScreeningID(), seats.toString(), Helper.convertDateToMillis(LocalDateTime.now()), toEdit.getRandString());
        // System.out.println(seats + " " + toEdit.getRandString());

        int returnCode = booking.saveBooking(); // trying the DB save
        if (!validationSupport.isInvalid() && returnCode == 1) { // DB upload was successful
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Booking is successful!");
            alert.setContentText("Your booking for "
                    + ((Movie) management.searchMovieByID(screeningPicker.getValue().getMovieID())).getMovieName()
                    + " on " + Helper.convertMillisToDateTime(screeningPicker.getValue().getScreeningTime()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    + " has been successfully placed. Your booking ID is " + booking.getBookingCode());
            alert.setTitle("Booking Confirmation");
            alert.showAndWait();
            Stage toKill = (Stage) makeBookingButton.getScene().getWindow();
            toKill.hide();
        } else { // Failed DB save
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println("ERror code:" + returnCode + "bookingid: " + booking.getBookingID());
            alert.setHeaderText("There was an error while making the booking!");
            alert.setTitle("Error");
            alert.setContentText(returnCode == -1 ? "A booking with that name already exists!" : "THERE IS NO HELP Booking ID Clash Error Code: " + returnCode + " BookingID: " + booking.getBookingID());
            alert.showAndWait();
        }
    }

    public void onScreeningPick() {
        // Checking if all the seats in the selected screening are available and then adding them to the list
        List<String> availableSeats = management.getAvailableSeatIDs(screeningPicker.getValue());
        for (Seat seat : ((Hall) management.searchHallByID(screeningPicker.getValue().getHallNumber())).getSeatList()) {
            if (availableSeats.contains(seat.getSeatID())) this.seatsTable.getItems().add(seat);
        }
    }

    public void editBooking(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("This feature has not been implemented yet!");
        alert.setTitle("Info");
        alert.setContentText("This feature is still being worked on, and hasn't been implemented yet.");
        alert.showAndWait();
    }
}
