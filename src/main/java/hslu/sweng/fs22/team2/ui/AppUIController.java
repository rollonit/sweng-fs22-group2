package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * Primary brains of the software, controls the behaviour of the main window.
 */
public class AppUIController {
    public ListView<String> viewSelector;
    public TableView<Object> centerTable;
    Management management;
    Stage primaryStage;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() throws SQLException {
        management = new Management();
        viewSelector.getItems().addAll("Screenings", "Movies", "Halls", "Bookings");
        //Detect changes in the selected item in the list
        viewSelector.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
            if (oldVal == null || !oldVal.equals(newVal)) this.updateTable();
        });
        //Select the first item by default
        viewSelector.getSelectionModel().select(0);
    }

    /**
     * FOR NOW spawns a login page and handles login validation. In the final program, this should probably show up first before the program boots, if the user is not already logged it
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    @FXML
    protected void onLoginPage() throws IOException {
        //Creating a new window
        Stage loginPrompt = new Stage();
        loginPrompt.setMinWidth(400);
        loginPrompt.setMinHeight(300);
        loginPrompt.setTitle("Login");

        Scene Login = new Scene((new FXMLLoader(AppUI.class.getResource("Login.fxml")).load()), 450, 300);
        loginPrompt.setScene(Login);

        loginPrompt.initOwner(welcomeText.getScene().getWindow());
        loginPrompt.initModality(Modality.APPLICATION_MODAL);

        loginPrompt.showAndWait();
    }

    /**
     * Spawns a new 'add movie' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onNewMoviePage() throws IOException {
        //Creating a new window
        Stage newMoviePrompt = new Stage();
        newMoviePrompt.setMinWidth(400);
        newMoviePrompt.setMinHeight(300);
        newMoviePrompt.setTitle("Create New Movie");

        Scene NewMovie = new Scene((new FXMLLoader(AppUI.class.getResource("NewMovie.fxml")).load()), 400, 300);
        newMoviePrompt.setScene(NewMovie);

        newMoviePrompt.initOwner(welcomeText.getScene().getWindow());
        newMoviePrompt.initModality(Modality.APPLICATION_MODAL);

        newMoviePrompt.showAndWait();
    }

    /**
     * Spawns a new 'edit movie' window based on fxml and passed the selected movie
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onEditMoviePage(Movie movieToEdit) throws IOException {
        //Creating a new window
        Stage newMoviePrompt = new Stage();
        newMoviePrompt.setMinWidth(400);
        newMoviePrompt.setMinHeight(300);
        newMoviePrompt.setTitle("Edit Existing Movie");

        FXMLLoader loader = new FXMLLoader(AppUI.class.getResource("EditMovie.fxml"));
        Scene EditMovie = new Scene(loader.load(), 400, 300);
        MovieController controller = loader.getController();
        controller.setToEdit(movieToEdit);
        newMoviePrompt.setScene(EditMovie);

        newMoviePrompt.initOwner(welcomeText.getScene().getWindow());
        newMoviePrompt.initModality(Modality.APPLICATION_MODAL);

        newMoviePrompt.showAndWait();
    }

    /**
     * Spawns a new 'add screening' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onNewScreeningPage() throws IOException {
        //Creating a new window
        Stage newScreeningPrompt = new Stage();
        newScreeningPrompt.setMinWidth(450);
        newScreeningPrompt.setMinHeight(360);
        newScreeningPrompt.setTitle("Add New Screening");

        Scene NewScreening = new Scene((new FXMLLoader(AppUI.class.getResource("NewScreening.fxml")).load()), 450, 360);
        newScreeningPrompt.setScene(NewScreening);

        newScreeningPrompt.initOwner(welcomeText.getScene().getWindow());
        newScreeningPrompt.initModality(Modality.APPLICATION_MODAL);

        newScreeningPrompt.showAndWait();
    }

    /**
     * Spawns a new 'edit screening' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onEditScreeningPage(Screening screeningToEdit) throws IOException {
        //Creating a new window
        Stage editScreeningPrompt = new Stage();
        editScreeningPrompt.setMinWidth(450);
        editScreeningPrompt.setMinHeight(360);
        editScreeningPrompt.setTitle("Edit Existing Screening");

        FXMLLoader loader = new FXMLLoader(AppUI.class.getResource("EditScreening.fxml"));
        Scene EditScreening = new Scene(loader.load(), 450, 360);
        ScreeningController controller = loader.getController();
        controller.setToEdit(screeningToEdit);
        editScreeningPrompt.setScene(EditScreening);

        editScreeningPrompt.initOwner(welcomeText.getScene().getWindow());
        editScreeningPrompt.initModality(Modality.APPLICATION_MODAL);

        editScreeningPrompt.showAndWait();
    }

    /**
     * Spawns a new 'make booking' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onNewBookingPage() throws IOException {
        //Creating a new window
        Stage newBookingPrompt = new Stage();
        newBookingPrompt.setMinWidth(550);
        newBookingPrompt.setMinHeight(650);
        newBookingPrompt.setTitle("Make New Booking");

        Scene NewBooking = new Scene((new FXMLLoader(AppUI.class.getResource("NewBooking.fxml")).load()), 550, 650);
        newBookingPrompt.setScene(NewBooking);

        newBookingPrompt.initOwner(welcomeText.getScene().getWindow());
        newBookingPrompt.initModality(Modality.APPLICATION_MODAL);

        newBookingPrompt.showAndWait();
    }

    /**
     * Spawns a new 'edit booking' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onEditBookingPage(Booking bookingToEdit) throws IOException {
        //Creating a new window
        Stage editBookingPrompt = new Stage();
        editBookingPrompt.setMinWidth(550);
        editBookingPrompt.setMinHeight(650);
        editBookingPrompt.setTitle("Edit Existing Booking");

        FXMLLoader loader = new FXMLLoader(AppUI.class.getResource("EditBooking.fxml"));
        Scene EditBooking = new Scene(loader.load(), 550, 650);
        BookingController controller = loader.getController();
        controller.setToEdit(bookingToEdit);
        editBookingPrompt.setScene(EditBooking);

        editBookingPrompt.initOwner(welcomeText.getScene().getWindow());
        editBookingPrompt.initModality(Modality.APPLICATION_MODAL);

        editBookingPrompt.showAndWait();
    }

    /**
     * Spawns a new 'create Hall' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onNewHallPage() throws IOException {
        //Creating a new window
        Stage newHallPrompt = new Stage();
        newHallPrompt.setMinWidth(400);
        newHallPrompt.setMinHeight(400);
        newHallPrompt.setTitle("Create New Hall");

        Scene NewHall = new Scene((new FXMLLoader(AppUI.class.getResource("NewHall.fxml")).load()), 400, 400);
        newHallPrompt.setScene(NewHall);

        newHallPrompt.initOwner(welcomeText.getScene().getWindow());
        newHallPrompt.initModality(Modality.APPLICATION_MODAL);

        newHallPrompt.showAndWait();
    }

    /**
     * Spawns a new 'edit Hall' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onEditHallPage(Hall hall) throws IOException {
        //Creating a new window
        Stage editHallPrompt = new Stage();
        editHallPrompt.setMinWidth(400);
        editHallPrompt.setMinHeight(400);
        editHallPrompt.setTitle("Edit Existing Hall");

        FXMLLoader loader = new FXMLLoader(AppUI.class.getResource("EditHall.fxml"));
        Scene EditHall = new Scene(loader.load(), 400, 400);
        HallController controller = loader.getController();
        controller.setToEdit(hall);
        editHallPrompt.setScene(EditHall);

        editHallPrompt.initOwner(welcomeText.getScene().getWindow());
        editHallPrompt.initModality(Modality.APPLICATION_MODAL);

        editHallPrompt.showAndWait();
    }

    /**
     * Spawns a new 'primary' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    /*public void onPrimaryPage() throws IOException {
        //Creating a new window
        Stage newPrimaryPage = new Stage();
        newPrimaryPage.setMinWidth(800);
        newPrimaryPage.setMinHeight(600);
        newPrimaryPage.setTitle("Primary Page");

        Scene NewScreening = new Scene((new FXMLLoader(AppUI.class.getResource("PrimaryPage.fxml")).load()), 800, 600);
        newPrimaryPage.setScene(NewScreening);

        newPrimaryPage.initOwner(welcomeText.getScene().getWindow());
        newPrimaryPage.initModality(Modality.APPLICATION_MODAL);

        newPrimaryPage.showAndWait();
    }
    */

    /**
     * Closes and exits the program.
     */
    public void onClose() {
        viewSelector.getScene().getWindow().hide();
    }

    /**
     * Handles the action for the new button, checks which view is selected, and spawns a create window
     * TODO add popup if nothing is selected
     */
    public void onNew() throws IOException {
        switch (viewSelector.getSelectionModel().getSelectedItem()) {
            case "Screenings" -> this.onNewScreeningPage();
            case "Movies" -> this.onNewMoviePage();
            case "Bookings" -> this.onNewBookingPage();
            case "Halls" -> this.onNewHallPage();
        }
    }

    /**
     * Handles the action for the edit button, checks which view is selected, which item is selected and does spawns edit window while setting the toEdit on the controller class.
     * TODO add popup if nothing is selected
     */
    public void onEdit() throws IOException {
        switch (viewSelector.getSelectionModel().getSelectedItem()) {
            case "Screenings" ->
                    this.onEditScreeningPage((Screening) centerTable.getSelectionModel().getSelectedItem());
            case "Movies" -> this.onEditMoviePage((Movie) centerTable.getSelectionModel().getSelectedItem());
            case "Bookings" -> this.onEditBookingPage((Booking) centerTable.getSelectionModel().getSelectedItem());
            case "Halls" -> this.onEditHallPage((Hall) centerTable.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Handles the action for the delete button, checks which view is selected, which item is selected and does delete
     * TODO add popup if nothing is selected
     */
    public void onDelete() throws SQLException {
        switch (viewSelector.getSelectionModel().getSelectedItem()) {
            case "Screenings" -> ((Screening) centerTable.getSelectionModel().getSelectedItem()).removeScreening();
            case "Movies" -> ((Movie) centerTable.getSelectionModel().getSelectedItem()).removeMovie();
            case "Bookings" -> ((Booking) centerTable.getSelectionModel().getSelectedItem()).removeBooking();
            case "Halls" -> ((Hall) centerTable.getSelectionModel().getSelectedItem()).removeHall();
        }
    }

    /**
     * Displays an alert to the user with the given parameters
     *
     * @param typeOfAlert The type of alert to display
     * @param title       the title of the alert dialog box
     * @param header      the text in the header section of the alert box
     * @param content     the content of the alert box
     */
    public void showAlert(Alert.AlertType typeOfAlert, String title, String header, String content) {
        Alert alert = new Alert(typeOfAlert);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Important function, updates the table items when a mouseclick is detected in the viewSelector list and populates it correctly.
     */
    public void updateTable() {
        centerTable.getColumns().clear();
        TableColumn<Object, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(25);
        TableColumn<Object, String> movieColumn = new TableColumn<>("Movie Name");
        movieColumn.setMinWidth(50);
        // Empty column prank
        TableColumn<Object, String> screeningDateColumn = new TableColumn<>("Screening Date");
        TableColumn<Object, String> screeningTimeColumn = new TableColumn<>("Screening Time");
        TableColumn<Object, String> bookingTimeColumn = new TableColumn<>("Booking Time");
        TableColumn<Object, String> bookingCodeColumn = new TableColumn<>("Booking Code");
        TableColumn<Object, String> hallWidthColumn = new TableColumn<>("Hall Width");
        TableColumn<Object, String> hallHeightColumn = new TableColumn<>("Hall Height");
        TableColumn<Object, String> releaseYearColumn = new TableColumn<>("Release Year");
        TableColumn<Object, String> directorColumn = new TableColumn<>("Director");
        TableColumn<Object, String> durationColumn = new TableColumn<>("Duration");


        // change Table view based on viewSelector Incredibly messy code to set up the data fields on the table.
        switch (viewSelector.getSelectionModel().getSelectedItem()) {
            case "Screenings" -> {
                idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Screening) cellData.getValue()).getScreeningID()));
                movieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Movie) management.searchMovieByID(((Screening) cellData.getValue()).getMovieID())).getMovieName()));
                screeningDateColumn.setCellValueFactory(data -> new SimpleStringProperty(Helper.convertMillisToDateTime(((Screening) data.getValue()).getScreeningTime()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                screeningTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(Helper.convertMillisToDateTime(((Screening) data.getValue()).getScreeningTime()).format(DateTimeFormatter.ofPattern("HH.mm"))));


                centerTable.getColumns().addAll(idColumn, movieColumn, screeningDateColumn, screeningTimeColumn);
                centerTable.setItems(FXCollections.observableArrayList(management.getScreeningList()));
            }
            case "Bookings" -> {
                idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Booking) cellData.getValue()).getBookingID()));
                movieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Movie) management.searchMovieByID(((Screening) management.searchScreeningByID(((Booking) cellData.getValue()).getScreeningID())).getMovieID())).getMovieName()));
                screeningTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(Helper.convertMillisToDateTime(((Screening) management.searchScreeningByID(((Booking) data.getValue()).getScreeningID())).getScreeningTime()).format(DateTimeFormatter.ofPattern("HH.mm"))));
                screeningDateColumn.setCellValueFactory(data -> new SimpleStringProperty(Helper.convertMillisToDateTime(((Screening) management.searchScreeningByID(((Booking) data.getValue()).getScreeningID())).getScreeningTime()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                bookingTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(Helper.convertMillisToDateTime(((Booking) data.getValue()).getBookingTime()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy @ HH.mm"))));
                bookingCodeColumn.setCellValueFactory(data -> new SimpleStringProperty(((Booking) data.getValue()).getBookingCode()));

                centerTable.getColumns().addAll(idColumn, movieColumn, screeningTimeColumn, screeningDateColumn, bookingTimeColumn, bookingCodeColumn);
                centerTable.setItems(FXCollections.observableArrayList(management.getBookingList()));
            }
            case "Halls" -> {
                idColumn.setCellValueFactory(data -> new SimpleStringProperty(((Hall) data.getValue()).getHallNumber()));
                hallWidthColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(((Hall) data.getValue()).getHallWidth())));
                hallHeightColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(((Hall) data.getValue()).getHallLength())));

                centerTable.getColumns().addAll(idColumn, hallWidthColumn, hallHeightColumn);
                centerTable.setItems(FXCollections.observableArrayList(management.getHallList()));
            }
            case "Movies" -> {
                idColumn.setCellValueFactory(data -> new SimpleStringProperty(((Movie) data.getValue()).getMovieID()));
                movieColumn.setCellValueFactory(data -> new SimpleStringProperty(((Movie) data.getValue()).getMovieName()));
                directorColumn.setCellValueFactory(data -> new SimpleStringProperty(((Movie) data.getValue()).getDirector()));
                releaseYearColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(((Movie) data.getValue()).getReleaseYear())));
                durationColumn.setCellValueFactory(data -> new SimpleStringProperty(Long.toString(((Movie) data.getValue()).getDuration().toMinutes())));

                centerTable.getColumns().addAll(idColumn, movieColumn, directorColumn, releaseYearColumn, durationColumn);
                centerTable.setItems(FXCollections.observableArrayList(management.getMovieList()));
            }
        }
    }

    public void refreshTable(ActionEvent actionEvent) {
        try {
            management.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.updateTable();
    }
}
