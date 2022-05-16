package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.Management;
import hslu.sweng.fs22.team2.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.List;

public class NewScreeningController {
    public Button addScreeningButton;
    public ComboBox moviePicker;
    public DatePicker datePicker;
    public ChoiceBox hallPicker;
    public TextField TimeField;

    private Management management;

    public void initialize() throws SQLException {
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
    }
}
