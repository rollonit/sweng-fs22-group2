package hslu.sweng.fs22.team2;

import java.util.Date;

/**
 * Represents a single screening of a certain movie on a certain date at a certain time in a certain hall
 */
public class Screening {
    /**
     * Screening ID for use as a private key.
     */
    private String screeningID;

    /**
     * The foreign key of hall in which the screening is being held.
     */
    private String hallNumber;

    /**
     * The foreign key for the movie being screened.
     */
    private String movieID;

    /**
     * The date and time at which the screening begins. The end time is determined by the duration of the movie.
     */
    private Date dateTime;

    /**
     * Default constructor for the Screening class.
     */
    public Screening() {
        dateTime = new Date();
    }

    /**
     * Parametrised constructor for the Screening class.
     *
     * @param screeningID the private key screening ID
     * @param movieID     the foreign key of the movie
     * @param hallNumber  the foreign key for the hall
     * @param dateTime    the date and time at which the movie is being screened
     */
    public Screening(String screeningID, String movieID, String hallNumber, Date dateTime) {
        this.screeningID = screeningID;
        this.movieID = movieID;
        this.hallNumber = hallNumber;
        this.dateTime = dateTime;
    }

    /**
     * Returns the screening with the given screening ID
     *
     * @param screeningID the ID for the screening to return
     * @return the screening with that ID, null if non-existent
     */
    public Screening getScreening(String screeningID) {
        // TODO create SQL Query/Statement
        return null;
    }

    /**
     * Creates a new screening in the database with the given parameters
     *
     * @param hallNumber the foreign key for the hall
     * @param movieID    the foreign key for the movie being played
     * @param dateTime   the date and time at which the screening starts
     */
    public void addScreening(String hallNumber, String movieID, Date dateTime) {
        // TODO create SQL Query/Statement
    }

    /**
     * Edits the hall in which the given screening is being played.
     *
     * @param ScreeningID   the screening which is to be edited
     * @param newHallNumber the foreign key for the updated hall
     */
    public void editHall(String ScreeningID, String newHallNumber) {
        // TODO create SQL Query/Statement
    }

    /**
     * Edits the movie which is being screened at a certain screening.
     *
     * @param ScreeningID the screening which is to be edited
     * @param newMovieID  the foreign key for the updated movie
     */
    public void editMovie(String ScreeningID, String newMovieID) {
        // TODO create SQL Query/Statement
    }

    /**
     * Edits the start date and/or time of the screening.
     *
     * @param ScreeningID the screening which is to be edited
     * @param newDateTime the updated date and time
     */
    public void editDateTime(String ScreeningID, Date newDateTime) {
        // TODO create SQL Query/Statement
    }

    /**
     * Removes the screening with the given ID from the database and returns it.
     *
     * @param screeningID the ID of the screening to be removed
     * @return the screening object if successfully removed, null if that screening doesn't exist
     */
    public Screening removeScreening(String screeningID) {
        // TODO create SQL Query/Statement
        return null;
    }
}
