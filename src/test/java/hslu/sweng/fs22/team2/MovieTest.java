package hslu.sweng.fs22.team2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private String movieName;
    private int releaseYear;
    private String director;
    private Duration duration;
    private Movie movie;
    private DBHandler databaseHandler;

    @BeforeEach
    void setUp() throws SQLException {
        this.movieName = "TestMovie";
        this.releaseYear = 2000;
        this.director = "";
        this.duration = Duration.ofMinutes(10);

        this.movie = new Movie("", this.movieName, this.releaseYear, this.director, this.duration);

        databaseHandler = new DBHandler();
    }

    @Test
    void getMovieID() throws SQLException {

        String result = this.movie.getMovieID();

        assertEquals(this.movie.generateMovieID(), result);

    }

    @Test
    void getMovieName() {

        String result = this.movie.getMovieName();

        assertEquals(this.movieName, result);
    }

    @Test
    void getReleaseYear() {
        int result = this.movie.getReleaseYear();

        assertEquals(this.releaseYear, result);
    }

    @Test
    void getDirector() {
        String result = this.movie.getDirector();

        assertEquals(this.director, result);
    }

    @Test
    void getDuration() {
        Duration result = this.movie.getDuration();

        assertEquals(this.duration, result);
    }

    @Test
    void addMovie() throws SQLException {
        int result = this.movie.saveMovie();

        assertEquals(1, result);
    }

    @Test
    void editMovie() {
    }

    @Test
    void removeMovie() throws SQLException {
        int resultFromAdd = this.movie.saveMovie();
        if(resultFromAdd == 1) {
            removeMovie();
            assertEquals(false, this.movie.doesExist());
        }
    }

    @Test
    void doesExist() throws SQLException {
        Boolean result  = this.movie.doesExist();

        assertEquals(false, result);
    }

    @Test
    void checkDuplicateName() {
    }
}