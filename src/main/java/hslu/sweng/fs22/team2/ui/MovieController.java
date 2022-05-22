package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.sql.SQLException;
import java.time.Duration;
import java.util.regex.Pattern;

/**
 * Handles anything to do with the UI functions required to handle movies (create, edit, delete, etc.), MUST run setToEdit() when run in edit mode.
 */
public class MovieController {
    /**
     * Handles validation for window fields.
     */
    private final ValidationSupport validationSupport = new ValidationSupport();
    //Various UI object handles
    public TextField movieNameField;
    public TextField directorField;
    public TextField yearField;
    public TextField durationField;
    public Button addMovieButton;
    /**
     * The screening that must be edited if the class is called in an edit mode.
     */
    Movie toEdit;

    /**
     * Initializes the validators for the fields in the new movie page.
     */
    @FXML
    public void initialize() {
        validationSupport.registerValidator(movieNameField, Validator.createEmptyValidator("Movie must have a name!"));
        validationSupport.registerValidator(yearField, Validator.createRegexValidator("Invalid year!", Pattern.compile("(?:(?:18|19|20|21)[0-9]{2})"), Severity.ERROR));
        validationSupport.registerValidator(directorField, Validator.createEmptyValidator("Must not me empty!"));
        validationSupport.registerValidator(durationField, Validator.createRegexValidator("Invalid duration!", Pattern.compile("[\\d]+"), Severity.ERROR));
        addMovieButton.disableProperty().bind(validationSupport.invalidProperty());
    }

    /**
     * Sets the movie on which the edit operations must be performed. MUST be run when called in an edit mode.
     *
     * @param toEdit the movie to be edited
     */
    public void setToEdit(Movie toEdit) {
        this.toEdit = toEdit;
        movieNameField.setText(toEdit.getMovieName());
        yearField.setText(Integer.toString(toEdit.getReleaseYear()));
        directorField.setText(toEdit.getDirector());
        durationField.setText(Long.toString(toEdit.getDuration().toMinutes()));
    }

    /**
     * Adds a new movie to the DB based on data from the UI fields, handles validation checks and errors with a popup.
     */
    public void addMovie() throws SQLException {
        Movie movie = new Movie("", movieNameField.getText(), Integer.parseInt(yearField.getText()), directorField.getText(), Duration.ofMinutes(Long.parseLong(durationField.getText())));
        int returnCode = movie.saveMovie();
        if (!validationSupport.isInvalid() && returnCode == 1) {
            Stage toKill = (Stage) addMovieButton.getScene().getWindow();
            toKill.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println("ERror code:" + returnCode + "movieid: " + movie.getMovieID());
            alert.setHeaderText("There was an error while adding the movie!");
            alert.setTitle("Error");
            alert.setContentText(returnCode == -1 ? "A movie with that name already exists!" : "THERE IS NO HELP Movie ID Clash Error Code: " + returnCode + " MovieID: " + movie.getMovieID());
            alert.showAndWait();
        }
    }

    /**
     * Edits the movie on the DB based on data from the UI fields. Handles validation.
     */
    public void editMovie() throws SQLException {
        toEdit.editMovie(movieNameField.getText(), Integer.parseInt(yearField.getText()), directorField.getText(), Duration.ofMinutes(Long.parseLong(durationField.getText())));
        if (!validationSupport.isInvalid()) {
            Stage toKill = (Stage) addMovieButton.getScene().getWindow();
            toKill.hide();
        }
    }
}
