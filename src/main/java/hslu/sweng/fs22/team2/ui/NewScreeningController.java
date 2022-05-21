package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.Management;
import hslu.sweng.fs22.team2.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class NewScreeningController {
    public Button addScreeningButton;
    public ComboBox moviePicker;
    public DatePicker datePicker;
    public ChoiceBox hallPicker;
    public TextField timeField;

    ValidationSupport validationSupport = new ValidationSupport();

    private Management management;

    public void initialize() throws SQLException {
        //Validators
        validationSupport.registerValidator(moviePicker, Validator.createEmptyValidator("Screening must have a movie!"));
        validationSupport.registerValidator(datePicker, Validator.createEmptyValidator("Screening must be on a date!"));
        validationSupport.registerValidator(timeField, Validator.createRegexValidator("Screening must have a time@!", Pattern.compile("^([ 01]?[0-9]|2[0-3]):([0-5][0-9])$"), Severity.ERROR));
        validationSupport.registerValidator(hallPicker, Validator.createEmptyValidator("Screening must be in a hall!"));
        addScreeningButton.disableProperty().bind(validationSupport.invalidProperty());

        //Movie Picker setup
        management = new Management();
        List<Movie> movieList = management.getMovieList();
        moviePicker.setItems(FXCollections.observableArrayList());

        for (Movie movie : movieList) {
            moviePicker.getItems().add(movie);
        }
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
    }

    public void addScreening() {
        //management;
    }
}
