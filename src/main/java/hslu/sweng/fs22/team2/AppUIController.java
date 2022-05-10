package hslu.sweng.fs22.team2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppUIController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
