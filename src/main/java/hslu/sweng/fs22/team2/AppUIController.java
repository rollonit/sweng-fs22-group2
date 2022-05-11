package hslu.sweng.fs22.team2;

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
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stage toChange = (Stage) welcomeText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AppUI.class.getResource("Login.fxml"));
        Scene Login = new Scene((new FXMLLoader(AppUI.class.getResource("Login.fxml")).load()), 800, 600);
        toChange.setScene(Login);
    }
}
