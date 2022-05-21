package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.validation.ValidationSupport;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingController {
    public ComboBox<Screening> screeningPicker;
    public Button makeBookingButton;
    public ListView<Seat> seatsTable;
    ValidationSupport validationSupport = new ValidationSupport();
    Booking toEdit;
    private Management management;

    public void initialize() throws SQLException, ParseException {
        management = new Management();
        screeningPicker.setItems(FXCollections.observableList(management.getScreeningList()));
        seatsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        screeningPicker.setConverter(new StringConverter<Screening>() {
            @Override
            public String toString(Screening screening) {
                if (screening == null) return "Please select";
                LocalDateTime timeOfScreening = Helper.convertMillisToDateTime(screening.getdateTime());
                return ((Movie) management.searchMovieByID(screening.getmovieID())).getMovieName() + " on " + timeOfScreening.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " at " + timeOfScreening.format(DateTimeFormatter.ofPattern("HH.mm"));
            }

            @Override
            public Screening fromString(String string) {
                return null;
            }
        });
    }

    public void setToEdit(Booking booking) {
        toEdit = booking;
    }

    public void makeBooking(ActionEvent actionEvent) {
    }

    public void onScreeningPick() {
        //Hall hallPicked = (Hall) management.searchHallByID(screeningPicker.getValue().gethallNumber());
        this.seatsTable.getItems().addAll((Seat) management.getAvailableSeatIDs(screeningPicker.getValue()));
    }

    public void editBooking(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("This feature has not been implemented yet!");
        alert.setTitle("Info");
        alert.setContentText("This feature is still being worked on, and hasn't been implemented yet.");
        alert.showAndWait();
    }
}
