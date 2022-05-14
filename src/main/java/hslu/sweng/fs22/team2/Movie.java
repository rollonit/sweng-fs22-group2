package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

/**
 * Represents a single movie playing at the theater.
 * TODO might need to add more info or a picture
 */
public class Movie {
    /**
     * Unique movie ID for use as a primary key.
     */
    private String movieID;
    /**
     * The name of the movie.
     */
    private String movieName;
    /**
     * The release year of the movie.
     */
    private int releaseYear;
    /**
     * The director of the movie.
     */
    private String director;
    /**
     * The duration of the movie.
     */
    private Duration duration;

    /**
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

    /**
     * Default constructor for the movie class.
     */
    public Movie() {
        releaseYear = 0;
        duration = Duration.ofMinutes(0);

    }

    /**
     * Parametrised constructor for the Movie class.
     *
     * @param movieID     the primary key for the movie
     * @param movieName   the name of the movie
     * @param releaseYear the year the movie was released
     * @param director    the director of the movie
     * @param duration    the duration of the movie
     * @param username    the username for the Database
     * @param password    the password for the Database
     */
    public Movie(String movieID, String movieName, int releaseYear, String director, Duration duration,String username, char[] password) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.duration = duration;

        this.databaseHandler = new DBHandler(username, password);
    }

    /**
     * @return the MovieID
     */
    public String getMovieID() {
        return this.movieID;
    }

    /**
     * @return the Movie Name
     */
    public String getMovieName() {
        return this.movieName;
    }

    /**
     * @return the Release Year
     */
    public Integer getReleaseYear() {
        return this.releaseYear;
    }

    /**
     * @return the Director
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * @return the Duration
     */
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Edits the Movie with the given parameters
     * Changes the object itself and write those changes to the Database as well
     *
     * @param movieName   the name of the movie
     * @param releaseYear the year the movie was released
     * @param director    the director of the movie
     * @param duration    the duration of the movie
     */
    public void editMovie(String movieName, int releaseYear, String director, Duration duration) {
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.duration = duration;

        double durationConverted = (double)duration.toMinutes();

        String queryText = String.format("UPDATE movie SET " +
                "movieName = '%s', " +
                "releaseYear = '%s', " +
                "director = '%s', " +
                "duration = %s " +
                "WHERE movieID = '%s';", movieName, releaseYear, director, durationConverted, this.movieID);

        databaseHandler.query(queryText);
    }

    /**
     * Removes the movie from the Database
     */
    public void removeMovie() {
        String queryText = String.format("DELETE FROM movie WHERE movieID = '%s'", this.movieID);
        this.databaseHandler.query(queryText);
    }

    public Boolean doesExist() throws SQLException {
        Integer count = 0;
        Boolean exists = false;

        String queryText = String.format("select COUNT(*) from movie WHERE movieID = '%s';", this.movieID);
        ResultSet rs = this.databaseHandler.query(queryText);

        while (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }

        if (count == 1) {
            exists = true;
        }
        else {
            exists = false;
        }
        return exists;
    }
}
