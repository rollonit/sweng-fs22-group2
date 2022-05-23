package hslu.sweng.fs22.team2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * <h2>The Cinema Booking System</h2>
 * This class contains the entry point for the GUI application and sets up required windows
 */
public class AppUI extends Application {

    Stage primaryStage;

    /**
     * Entry point for the application to run with a UI. Launches JavaFX
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Start point for JavaFX creates the primary stage, loads FXML and sets up the window dimensions, title and icons
     *
     * @param window the primary stage for this application, onto which
     *               the application scene can be set.
     *               Applications may create other stages, if needed, but they will not be
     *               primary stages.
     * @throws IOException if it can't find the FXML or the icon for the main window
     */
    @Override
    public void start(Stage window) throws IOException {
        primaryStage = window;
        FXMLLoader fxmlLoader = new FXMLLoader(AppUI.class.getResource("PrimaryPage.fxml"));
        Scene MainPage = new Scene(fxmlLoader.load(), 900, 600);


        // Set up window settings
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(900);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(AppUI.class.getResourceAsStream("icon.png"))));


        primaryStage.setTitle("Cinema Booking System");
        primaryStage.setScene(MainPage);

        // Spawns a login window that validates itself
        Stage loginPrompt = new Stage();
        loginPrompt.setMinWidth(400);
        loginPrompt.setMinHeight(300);
        loginPrompt.setTitle("Login");
        Scene Login = new Scene((new FXMLLoader(AppUI.class.getResource("Login.fxml")).load()), 450, 300);
        loginPrompt.setScene(Login);
        loginPrompt.showAndWait();

        // start main program
        primaryStage.show();
    }
}
