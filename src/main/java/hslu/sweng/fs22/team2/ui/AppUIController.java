package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.AppUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppUIController {
    Stage primaryStage;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
    }

    /**
     * FOR NOW spawns a login page and handles login validation. In the final program, this should probably show up first before the program boots, if the user is not already logged it
     *
     * @throws IOException in case it doesn't find the fxml file ig
     */
    @FXML
    protected void onLoginPage() throws IOException {
        Stage loginPrompt = new Stage();
        loginPrompt.setMinWidth(400);
        loginPrompt.setMinHeight(300);

        Scene Login = new Scene((new FXMLLoader(AppUI.class.getResource("Login.fxml")).load()), 450, 300);
        loginPrompt.setScene(Login);

        loginPrompt.initOwner(welcomeText.getScene().getWindow());
        loginPrompt.initModality(Modality.APPLICATION_MODAL);

        loginPrompt.showAndWait();
    }

    /**
     * Spawns a new 'add movie' window based on fxml
     *
     * @param actionEvent the event which triggered this function
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onNewMoviePage(ActionEvent actionEvent) throws IOException {
        Stage newMoviePrompt = new Stage();
        newMoviePrompt.setMinWidth(400);
        newMoviePrompt.setMinHeight(300);

        Scene NewMovie = new Scene((new FXMLLoader(AppUI.class.getResource("NewMovie.fxml")).load()), 400, 300);
        newMoviePrompt.setScene(NewMovie);

        newMoviePrompt.initOwner(welcomeText.getScene().getWindow());
        newMoviePrompt.initModality(Modality.APPLICATION_MODAL);

        newMoviePrompt.showAndWait();
    }

    /**
     * Spawns a new 'add screening' window based on fxml
     *
     * @param actionEvent the event which triggered this function
     * @throws IOException in case it doesn't find the fxml file ig
     */
    public void onNewScreeningPage(ActionEvent actionEvent) throws IOException {
        Stage newScreeningPrompt = new Stage();
        newScreeningPrompt.setMinWidth(450);
        newScreeningPrompt.setMinHeight(360);

        Scene NewScreening = new Scene((new FXMLLoader(AppUI.class.getResource("NewScreening.fxml")).load()), 450, 360);
        newScreeningPrompt.setScene(NewScreening);

        newScreeningPrompt.initOwner(welcomeText.getScene().getWindow());
        newScreeningPrompt.initModality(Modality.APPLICATION_MODAL);

        newScreeningPrompt.showAndWait();
    }
}
