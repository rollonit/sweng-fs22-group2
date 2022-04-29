package hslu.sweng.fs22.team2;

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
    public Movie(String movieID, String movieName, int releaseYear, String director, Duration duration) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.duration = duration;
    }

    /**
     * Returns a movie given the movie ID.
     *
     * @param movieID the ID of the movie to return
     * @return the requested movie object
     */
    public Movie getMovie(String movieID) {
        // TODO create SQL Query/Statement
        return null;
    }

    /**
     * Edits the name of the movie at the given ID.
     *
     * @param movieID the ID of the movie whose name must be changed
     * @param newName the new name to apply to the movie
     */
    public void editName(String movieID, String newName) {
        // TODO create SQL Query/Statement
    }

    /**
     * Edits the release year of the movie at the given ID.
     *
     * @param movieID        the ID of the movie whose release year must be changed
     * @param newReleaseYear the new release year to apply to the movie
     */
    public void editReleaseYear(String movieID, int newReleaseYear) {
        // TODO create SQL Query/Statement
    }

    /**
     * Edits the director of the movie at the given ID.
     *
     * @param movieID     the ID of the movie whose director must be changed
     * @param newDirector the new director to apply to the movie
     */
    public void editDirector(String movieID, String newDirector) {
        // TODO create SQL Query/Statement
    }

    /**
     * Edits the duration of the movie at the given ID.
     *
     * @param movieID     the ID of the movie whose duration must be changed
     * @param newDuration the new duration to apply to the movie
     */
    public void editDuration(String movieID, Duration newDuration) {
        // TODO create SQL Query/Statement
    }

    /**
     * Creates a new movie in the database with the given parameters.
     *
     * @param movieName   the name of the movie to be created
     * @param releaseYear the release year of the movie
     * @param director    the director of the movie
     * @param duration    the duration of the movie
     */
    public void createMovie(String movieName, int releaseYear, String director, Duration duration) {
        // TODO create SQL Query/Statement
    }
}
