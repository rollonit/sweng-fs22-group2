package hslu.sweng.fs22.team2.ui;


import hslu.sweng.fs22.team2.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Handles anything to do with the UI functions required to handle screenings (create, edit, delete, etc.), MUST run setToEdit() when run in edit mode.
 */
public class ScreeningController {
    /**
     * Handles validation for window fields.
     */
    private final ValidationSupport validationSupport = new ValidationSupport();
    // Various UI element handles
    public Button addScreeningButton;
    public ComboBox<Movie> moviePicker;
    public DatePicker datePicker;
    public ChoiceBox<Hall> hallPicker;
    public TextField timeField;
    /**
     * The screening that must be edited if the class is called in an edit mode.
     */
    Screening toEdit;
    /**
     * Management class for DB operations.
     */
    private Management management;

    /**
     * Initialises the window and the validators. It also loads required data for the dropdown menus on the screen.
     */
    @FXML
    public void initialize() throws SQLException {
        //Validators
        validationSupport.registerValidator(moviePicker, Validator.createEmptyValidator("Screening must have a movie!"));
        validationSupport.registerValidator(datePicker, Validator.createEmptyValidator("Screening must be on a date!"));
        validationSupport.registerValidator(timeField, Validator.createRegexValidator("Screening must have a time@!", Pattern.compile("^([01][0-9]|2[0-3]):([0-5][0-9])$"), Severity.ERROR));
        validationSupport.registerValidator(hallPicker, Validator.createEmptyValidator("Screening must be in a hall!"));
        addScreeningButton.disableProperty().bind(validationSupport.invalidProperty());

        //Movie Picker setup
        management = new Management();

        moviePicker.setItems(FXCollections.observableList(management.getMovieList()));
        moviePicker.setConverter(new StringConverter<Movie>() {
            @Override
            public String toString(Movie movie) {
                if (movie == null) return "Please select";
                return movie.getMovieName();
            }

            @Override
            public Movie fromString(String string) {
                return null;
            }
        });

        //Hall Picker setup
        hallPicker.setItems(FXCollections.observableList(management.getHallList()));
        hallPicker.setConverter(new StringConverter<Hall>() {
            @Override
            public String toString(Hall hall) {
                if (hall == null) return "Please select";
                return hall.getHallNumber();
            }

            @Override
            public Hall fromString(String string) {
                return null;
            }
        });

        addScreeningButton.setDefaultButton(true);
    }

    /**
     * Sets the screening on which the edit operations must be performed. MUST be run when called in an edit mode.
     *
     * @param screening the screening to be edited
     */
    public void setToEdit(Screening screening) {
        toEdit = screening;
        hallPicker.getSelectionModel().select((Hall) management.searchHallByID(screening.getHallNumber()));
        moviePicker.getSelectionModel().select((Movie) management.searchMovieByID(screening.getMovieID()));
        timeField.setText(Helper.convertMillisToDateTime(screening.getScreeningTime()).format(DateTimeFormatter.ofPattern("HH:mm")));
        datePicker.setValue(Helper.convertMillisToDateTime(screening.getScreeningTime()).toLocalDate());
    }

    /**
     * Adds a new screening to the DB based on data from the UI fields, handles validation checks and errors with a popup.
     */
    public void addScreening() throws SQLException {
        String timeDate = timeField.getText() + " " + datePicker.getValue().toString();
        Screening sc = new Screening("", moviePicker.getValue().getMovieID(), hallPicker.getValue().getHallNumber(), Helper.convertTextToMillis(timeDate));
        System.out.println("" + ", " + moviePicker.getValue().getMovieID() + ", " + hallPicker.getValue().getHallNumber() + ", " + Helper.convertTextToMillis(timeDate)); //debug
        int returnCode = sc.saveScreening();
        if (!validationSupport.isInvalid() && returnCode == 1) {
            Stage toKill = (Stage) addScreeningButton.getScene().getWindow();
            toKill.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println("ERror code:" + returnCode + "screeningid: " + sc.getScreeningID());
            alert.setHeaderText("There was an error while adding the screening!");
            alert.setTitle("Error");
            alert.setContentText(returnCode == -1 ? "A screening with that name already exists!" : "THERE IS NO HELP Screening ID Clash Error Code: " + returnCode + " ScreeningID: " + sc.getScreeningID());
            alert.showAndWait();
        }
    }

    /**
     * Edits the screening on the DB based on data from the UI fields. Handles validation.
     */
    public void editScreening() throws SQLException {
        String timeDate = timeField.getText() + " " + datePicker.getValue().toString();
        toEdit.editScreening(Helper.convertTextToMillis(timeDate), hallPicker.getValue().getHallNumber(), moviePicker.getValue().getMovieID());
        if (!validationSupport.isInvalid()) {
            Stage toKill = (Stage) addScreeningButton.getScene().getWindow();
            toKill.hide();
        }
    }
}
