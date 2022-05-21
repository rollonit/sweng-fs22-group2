package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    private long screeningTime;

    /**
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

    /**
     * Default constructor for the Screening class.
     */
    public Screening() {
    }

    /**
     * Parametrised constructor for the Screening class.
     *
     * @param screeningID   the private key screening ID
     * @param movieID       the foreign key of the movie
     * @param hallNumber    the foreign key for the hall
     * @param screeningTime the date and time at which the movie is being screened
     */
    public Screening(String screeningID, String movieID, String hallNumber, long screeningTime) throws SQLException {
        this.screeningID = screeningID;
        this.movieID = movieID;
        this.hallNumber = hallNumber;
        this.screeningTime = screeningTime;

        this.databaseHandler = new DBHandler();

        if (this.screeningID.isEmpty()) {

            this.screeningID = (generateScreeningID());
        }
    }

    /**
     * @return The ScreeningID
     */
    public String getScreeningID() {
        return this.screeningID;
    }

    /**
     * @return The MovieID
     */
    public String getMovieID() {
        return this.movieID;
    }

    /**
     * @return hallNumber
     */
    public String getHallNumber() {
        return this.hallNumber;
    }

    /**
     * @return the screening time
     */
    public long getScreeningTime() {
        return this.screeningTime;
    }


    /**
     * Saves the screening object to the Database
     */
    public int saveScreening() throws SQLException {
        int returnCode = 0;

        if (!(doesExist())) {
            String queryText = String.format("insert into screening " +
                            "(screeningID, screeningTime, hallNumber, movieID) values " +
                            "('%s', '%s', '%s', '%s');",
                    this.screeningID, this.screeningTime, this.hallNumber, this.movieID);
            ResultSet rs = this.databaseHandler.query(queryText);
            returnCode = 1;
        } else {
            System.out.println("ScreeningID Already in Database");
            returnCode = -2;
        }
        return returnCode;
    }

    /**
     * Edits the screening with the given parameters
     * Changes the object itself and write those changes to the Database as well
     *
     * @param screeningTime the date of the screening
     * @param hallNumber    the number of the hall for the screening
     * @param movieID       the id of the movie
     */
    public void editScreening(long screeningTime, String hallNumber, String movieID) throws SQLException {
        this.screeningTime = screeningTime;
        this.hallNumber = hallNumber;
        this.movieID = movieID;

        if (doesExist()) {
            String queryText = String.format("UPDATE screening SET " +
                    "screeningTime = '%s', " +
                    "hallNumber = '%s', " +
                    "movieID = '%s' " +
                    "WHERE screeningID = '%s';", screeningTime, hallNumber, movieID, this.screeningID);
            this.databaseHandler.query(queryText);
        }
    }

    /**
     * Removes the screening from the Database
     */
    public void removeScreening() throws SQLException {
        if (doesExist()) {
            String queryText = String.format("DELETE FROM screening WHERE screeningID = '%s'", this.screeningID);
            this.databaseHandler.query(queryText);
        }
    }


    /**
     * Checks if the Screening exists in the Database
     */
    public boolean doesExist() throws SQLException {
        int count = 0;
        boolean exists = false;

        String queryText = String.format("select COUNT(*) from screening WHERE screeningID = '%s';", this.screeningID);
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
     * Generates new ScreeningID by checking the database
     */
    public String generateScreeningID() throws SQLException {

        ResultSet rs = databaseHandler.query("SELECT screeningID FROM screening order by screeningID desc LIMIT 1;");
        String latestName = "";
        while (rs.next()) {
            latestName = rs.getString("screeningID");
        }

        int screeningID = Integer.parseInt(latestName);
        screeningID++;
        return Integer.toString(screeningID);
    }
}
