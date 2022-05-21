package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.AppUI;
import hslu.sweng.fs22.team2.Management;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Primary brains of the software, controls the behaviour of the main window.
 */
public class AppUIController {
    public ListView<String> viewSelector;
    Management management;
    Stage primaryStage;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() throws SQLException, ParseException {
        management = new Management();
        viewSelector.getItems().addAll("Screenings", "Movies", "Halls", "Bookings");
        //Detect changes in the selected item in the list
        viewSelector.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
            if (!oldVal.equals(newVal)) this.updateTable();
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

        Scene NewScreening = new Scene((new FXMLLoader(AppUI.class.getResource("NewBooking.fxml")).load()), 550, 650);
        newBookingPrompt.setScene(NewScreening);

        newBookingPrompt.initOwner(welcomeText.getScene().getWindow());
        newBookingPrompt.initModality(Modality.APPLICATION_MODAL);

        newBookingPrompt.showAndWait();
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

        Scene NewScreening = new Scene((new FXMLLoader(AppUI.class.getResource("NewHall.fxml")).load()), 400, 400);
        newHallPrompt.setScene(NewScreening);

        newHallPrompt.initOwner(welcomeText.getScene().getWindow());
        newHallPrompt.initModality(Modality.APPLICATION_MODAL);

        newHallPrompt.showAndWait();
    }

    /**
     * Spawns a new 'primary' window based on fxml
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onPrimaryPage() throws IOException {
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

    /**
     * Closes and exits the program.
     */
    public void onClose() {
        viewSelector.getScene().getWindow().hide();
    }

    public void onNew() throws IOException {
        switch (viewSelector.getSelectionModel().getSelectedItem()) {
            case "Screenings" -> this.onNewScreeningPage();
            case "Movies" -> this.onNewMoviePage();
            case "Bookings" -> this.onNewBookingPage();
            case "Halls" -> this.onNewHallPage();
        }
    }

    public void onEdit() {

    }

    public void onDelete() {
    }

    /**
     * Important function, updates the table items when a mouseclick is detected in the viewSelector list and populates it correctly.
     */
    public void updateTable() {
        // debug System.out.println(viewSelector.getSelectionModel().getSelectedItem());

    }
}
