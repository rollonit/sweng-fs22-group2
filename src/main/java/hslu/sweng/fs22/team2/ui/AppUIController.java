package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.AppUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AppUIController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoginPage() throws IOException {
        Stage loginPrompt = new Stage();
        loginPrompt.setMinWidth(400);
        loginPrompt.setMinHeight(300);

        Scene Login = new Scene((new FXMLLoader(AppUI.class.getResource("Login.fxml")).load()), 450, 300);
        loginPrompt.setScene(Login);

        loginPrompt.showAndWait();
    }

    /**
     * Spawns a new movie add window based on fxml
     * @param actionEvent the event which triggered this function
     * @throws IOException in case it doesn't find the fxml file
     */
    public void onNewMoviePage(ActionEvent actionEvent) throws IOException {
        Stage newMoviePrompt = new Stage();
        newMoviePrompt.setMinWidth(400);
        newMoviePrompt.setMinHeight(300);

        Scene NewMovie = new Scene((new FXMLLoader(AppUI.class.getResource("NewMovie.fxml")).load()), 400, 300);
        newMoviePrompt.setScene(NewMovie);

        newMoviePrompt.showAndWait();
    }

    public void onNewShowingPage(ActionEvent actionEvent) {
    }
}
