package hslu.sweng.fs22.team2.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class LoginController {
    private final ValidationSupport validationSupport = new ValidationSupport();
    public PasswordField passwordField;
    public Button loginButton;
    public TextField usernameField;

    @FXML
    public void initialize() {
        validationSupport.registerValidator(usernameField, Validator.createEmptyValidator("A username is required!"));
        loginButton.disableProperty().bind(validationSupport.invalidProperty());
    }

    /**
     * Validates the login information and handles server login.
     */
    public void validateLogin() {
        //TODO do login actions

        if (!validationSupport.isInvalid()) {
            //cleanup
            Stage toKill = (Stage) passwordField.getScene().getWindow();
            toKill.hide();
        }
    }
}
