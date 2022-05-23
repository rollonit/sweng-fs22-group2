package hslu.sweng.fs22.team2.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Handles the UI functions required with login window, such as validation.
 */
public class LoginController {
    private final ValidationSupport validationSupport = new ValidationSupport();
    public PasswordField passwordField;
    public Button loginButton;
    public TextField usernameField;

    /**
     * Initialises the validators for the login window.
     */
    @FXML
    public void initialize() {
        validationSupport.registerValidator(usernameField, Validator.createEmptyValidator("A username is required!"));
        loginButton.disableProperty().bind(validationSupport.invalidProperty());
        loginButton.setDefaultButton(true);
    }

    /**
     * Validates the login information and handles server login.
     */
    public void validateLogin() {
        // TEST CREDENTIALS
        String username = "employee1";
        String pass = "admin";

        if (!validationSupport.isInvalid() && usernameField.getText().equals(username) && passwordField.getText().equals(pass)) {
            //cleanup
            Stage toKill = (Stage) passwordField.getScene().getWindow();
            toKill.hide();
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Wrong credentials!");
                alert.setTitle("Error");
                alert.setContentText("Hint: sample username: employee1, sample password: admin");
                alert.showAndWait();
        }
    }
}
