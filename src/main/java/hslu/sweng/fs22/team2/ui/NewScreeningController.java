package hslu.sweng.fs22.team2.ui;


import hslu.sweng.fs22.team2.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

public class NewScreeningController {
    public Button addScreeningButton;
    public ComboBox<Movie> moviePicker;
    public DatePicker datePicker;
    public ChoiceBox<Hall> hallPicker;
    public TextField timeField;

    ValidationSupport validationSupport = new ValidationSupport();

    private Management management;

    public void initialize() throws SQLException, ParseException {
        //Validators
        validationSupport.registerValidator(moviePicker, Validator.createEmptyValidator("Screening must have a movie!"));
        validationSupport.registerValidator(datePicker, Validator.createEmptyValidator("Screening must be on a date!"));
        validationSupport.registerValidator(timeField, Validator.createRegexValidator("Screening must have a time@!", Pattern.compile("^([ 01]?[0-9]|2[0-3]):([0-5][0-9])$"), Severity.ERROR));
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
    }

    public void addScreening() throws ParseException {
        String timeDate = timeField.getText() + " " + datePicker.getValue().toString();
        System.out.println(timeDate);
        System.out.println(Helper.convertTextToDate(timeDate));
        //Screening sc = new Screening("", moviePicker.getValue().getMovieID(), hallPicker.getValue().getHallNumber(), Helper.convertDateToTicks(Helper.convertTextToDate(timeField.getText()+" "+Helper.convertTextToDate(datePicker.getValue().toString()))));

    }
}
