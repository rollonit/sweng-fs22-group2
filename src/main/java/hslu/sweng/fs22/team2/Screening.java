package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.SQLException;
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
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

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
     * @return The ScreeningID
     */
    public String getScreeningID() {
        return this.screeningID;
    }

    /**
     * @return The MovieID
     */
    public String getmovieID() {
        return this.movieID;
    }

    /**
     * @return hallNumber
     */
    public String gethallNumber() {
        return this.hallNumber;
    }

    /**
     * @return hallNumber
     */
    public Date getdateTime() {
        return this.dateTime;
    }



    /**
     * Saves the screening object to the Database
     *
     */
    public int saveScreening() throws SQLException{
        int returnCode = 0;

        if(!(doesExist())) {
            String queryText = String.format("insert into screening " +
                            "(screeningID, dateTime, hallNumber, movieID) values " +
                            "('%s', '%s', '%s', '%s');",
                    this.screeningID, this.dateTime,toString(), this.hallNumber, this.movieID);
            ResultSet rs = this.databaseHandler.query(queryText);
            returnCode = 1;
        }else{
            System.out.println("HallNumber Already in Database");
            returnCode = -2;
        }
        return returnCode;
    }


    public Screening removeScreening(String screeningID) {
        // TODO create SQL Query/Statement
        return null;
    }


    /**
     * Checks if the Screening exists in the Database
     */
    public boolean doesExist() throws SQLException {
        int count = 0;
        boolean exists = false;

        String queryText = String.format("select COUNT(*) from screening WHERE screeningID = '%s';", this.hallNumber);
        ResultSet rs = this.databaseHandler.query(queryText);

        while (rs.next()) {
            count = rs.getInt("COUNT(*)");
        }

        if (count == 1) {
            exists = true;
        }
        return exists;
    }
}
