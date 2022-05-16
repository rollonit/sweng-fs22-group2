package hslu.sweng.fs22.team2.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.util.regex.Pattern;

/**
 * Controller for the add movie page.
 */
public class NewMovieController {
    private final ValidationSupport validationSupport = new ValidationSupport();
    public TextField movieNameField;
    public TextField directorField;
    public TextField yearField;
    public TextField durationField;
    public Button addMovieButton;

    /**
     * Initializes the validators for the fields in the new movie page.
     */
    @FXML
    public void initialize() {
        validationSupport.registerValidator(movieNameField, Validator.createEmptyValidator("Movie must have a name!"));
        validationSupport.registerValidator(yearField, Validator.createRegexValidator("Invalid year!", Pattern.compile("(?:(?:18|19|20|21)[0-9]{2})"), Severity.ERROR));
        validationSupport.registerValidator(directorField, Validator.createEmptyValidator("Must not me empty!"));
        validationSupport.registerValidator(durationField, Validator.createRegexValidator("Invalid year!", Pattern.compile("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$"), Severity.ERROR));
        addMovieButton.disableProperty().bind(validationSupport.invalidProperty());

    }

    /**
     * Validates the new movie info, and handles adding a new movie.
     */
    public void addMovie() {
        // TODO do add movie actions
        if (!validationSupport.isInvalid()) {
            Stage toKill = (Stage) addMovieButton.getScene().getWindow();
            toKill.hide();
        }
    }
}
