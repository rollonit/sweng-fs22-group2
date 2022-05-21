package hslu.sweng.fs22.team2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

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
    void saveMovie() throws SQLException {
        int result = this.movie.saveMovie();

        assertEquals(1, result);

        this.movie.removeMovie();

    }

    @Test
    void editMovie() throws SQLException {

        String movieName2 = "TestMovie2";
        int releaseYear2 = 2001;
        String director2 = "abc";
        Duration duration2 = Duration.ofMinutes(100);

        String movieNameFromQuery = "";
        int releaseYearFromQuery = 0;
        String directorFromQuery = "";
        double durationFromQuery = 0.0;


        if(!this.movie.doesExist()) {
            this.movie.saveMovie();
        }

        this.movie.editMovie(movieName2, releaseYear2, director2, duration2);

        boolean check = false;
        String queryText = String.format("SELECT * FROM movie WHERE movieID = '%s';", this.movie.getMovieID());
        ResultSet rs = databaseHandler.query(queryText);
        while(rs.next())
        {
            movieNameFromQuery = rs.getString("movieName");
            releaseYearFromQuery = rs.getInt("releaseYear");
            directorFromQuery = rs.getString("director");
            durationFromQuery = rs.getDouble("duration");
        }

        int durationToInt = (int) Math.round(durationFromQuery);
        Duration durationConverted = Duration.of((long) durationToInt, ChronoUnit.MINUTES);

        if(movieNameFromQuery.equals(movieName2) &&
                releaseYearFromQuery == releaseYear2 &&
                directorFromQuery.equals(director2) &&
                durationConverted.equals(duration2)
        ){
            check = true;
        }

        assertEquals(true, check);

        this.movie.removeMovie();

    }

    @Test
    void removeMovie() throws SQLException {
        if(!this.movie.doesExist()) {
            this.movie.saveMovie();
        }
        this.movie.removeMovie();
        boolean check = this.movie.doesExist();
        assertEquals(false, check);
    }

    @Test
    void doesExist() throws SQLException {
        Boolean result  = this.movie.doesExist();

        assertEquals(false, result);
    }

    @Test
    void generateMovieID() throws SQLException{
        ResultSet rs = databaseHandler.query("SELECT movieID FROM movie order by movieID desc LIMIT 1;");
        String latestName = "";
        while (rs.next()) {
            latestName = rs.getString("movieID");
        }

        int movieIDNumber = Integer.parseInt(latestName);
        movieIDNumber++;

        String shouldbe = this.movie.generateMovieID();
        assertEquals(shouldbe, (Integer.toString(movieIDNumber)));
    }
}