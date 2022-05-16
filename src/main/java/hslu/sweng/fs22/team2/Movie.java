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
     */
    public Movie(String movieID, String movieName, int releaseYear, String director, Duration duration) throws SQLException {
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.duration = duration;

        this.databaseHandler = new DBHandler();

        double durationConverted = (double) duration.toMinutes();
        String queryText = String.format("SELECT movieID FROM movie WHERE " +
                "movieName = '%s' and " +
                "releaseYear = '%s' and " +
                "director = '%s' and " +
                "duration = '%s';", this.movieName, this.releaseYear, this.director, durationConverted);
        ResultSet rs = databaseHandler.query(queryText);
        String movieIDFromQuery = "";
        while (rs.next()) {
            movieIDFromQuery = rs.getString("movieID");
        }

        if (!movieIDFromQuery.isEmpty()) {
            this.movieID = movieIDFromQuery;
        }

        if (this.movieID.isEmpty()) {

            this.movieID = (generateMovieID());
        }
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
    public int getReleaseYear() {
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
     * Adds movie to the Database
     */
    public int addMovie() throws SQLException {
        int returnCode = 0;

        if (!(doesExist())) {
            if (!(checkDuplicateName())) {
                double durationConverted = (double) this.duration.toMinutes();
                String queryText = String.format("INSERT INTO movie (movieID, movieName, releaseYear, director, duration) VALUE ('%s', '%s', %s, '%s', %s);",
                        this.movieID, this.movieName, this.releaseYear, this.director, durationConverted);
                ResultSet rs = this.databaseHandler.query(queryText);

                returnCode = 1;
            } else {
                System.out.println("MovieName Already in Database");
                returnCode = -1;
            }
        } else {
            System.out.println("MovieID Already in Database");
            returnCode = -2;
        }

        return returnCode;
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

        double durationConverted = (double) duration.toMinutes();

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
    public void removeMovie() throws SQLException {
        if (doesExist()) {
            String queryText = String.format("DELETE FROM movie WHERE movieID = '%s'", this.movieID);
            this.databaseHandler.query(queryText);
        } else {
            System.out.println("No Movie with ID: " + this.movieID + " in Database");
        }

    }

    public Boolean doesExist() throws SQLException {
        int count = 0;
        boolean exists = false;

        String queryText = String.format("select COUNT(*) from movie WHERE movieID = '%s';", this.movieID);
        ResultSet rs = this.databaseHandler.query(queryText);

        while (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }

        if (count == 1) {
            exists = true;
        }
        return exists;
    }

    /**
     * checks if movieName creates duplicates
     */
    public Boolean checkDuplicateName() throws SQLException {
        int count = 0;
        boolean exists = false;

        String queryText = String.format("select COUNT(*) from movie WHERE movieName = '%s';", this.movieName);
        ResultSet rs = this.databaseHandler.query(queryText);

        while (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }

        if (count > 0) {
            exists = true;
        }
        return exists;
    }

    public String generateMovieID() throws SQLException {

        ResultSet rs = databaseHandler.query("SELECT movieID FROM movie order by movieID desc LIMIT 1;");
        String latestName = "";
        while (rs.next()) {
            latestName = rs.getString("movieID");
        }

        int movieIDNumber = Integer.parseInt(latestName);
        movieIDNumber++;
        return Integer.toString(movieIDNumber);
    }
}