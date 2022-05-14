package hslu.sweng.fs22.team2;

/**
 * Represents a single hall in the theater.
 */
public class Hall {
    /**
     * Unique hall number for use as a private key and for display.
     */
    private String hallNumber;
    /**
     * The number of seats in each row of the hall.
     */
    private int hallWidth;

    /**
     * The number of rows in the theater.
     */
    private int hallLength;

    /**
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

    /**
     * Default constructor for the Hall class.
     */
    public Hall() {
        hallNumber = "";
        hallWidth = 0;
        hallLength = 0;
    }

    /**
     * Parametrised constructor for the Hall class.
     *
     * @param hallNumber the number to assign to hall
     * @param hallWidth  the number of seats in each row of the hall
     * @param hallLength the number of rows of seats in the hall
     * @param username   the username for the Database
     * @param password   the password for the Database
     */
    public Hall(String hallNumber, int hallWidth, int hallLength,String username, char[] password) {
        this.hallNumber = hallNumber;
        this.hallWidth = hallWidth;
        this.hallLength = hallLength;

        this.databaseHandler = new DBHandler(username, password);
    }

    /**
     * @Returns The Hall Number.
     */
    public String getHallNumber() {
        return this.hallNumber;
    }

    /**
     * @Returns The Hall Width.
     */
    public Integer getHallWidth() {
        return this.hallWidth;
    }

    /**
     * @Returns The Hall Length.
     */
    public Integer getHallLength() {
        return this.hallLength;
    }

    /**
     * Edits the Hall with the given parameters
     * Changes the object itself and write those changes to the Database as well
     *
     * @param hallWidth  the number of seats in each row of the hall
     * @param hallLength the number of rows of seats in the hall
     */
    public void editHall(Integer hallWidth, Integer hallLength) {
        this.hallWidth = hallWidth;
        this.hallLength = hallLength;

        String queryText = String.format("UPDATE hall SET " +
                "hallWidth = '%s', " +
                "hallLength = '%s', " +
                "WHERE hallNumber = '%s';", hallWidth, hallLength, this.hallNumber);
    }

    /**
     * Populates the given hall with seats in the database.
     *
     * @param hallNumberToPopulate the hall which is to be populated with seats
     */
    public void createSeats(String hallNumberToPopulate) {
        // TODO create SQL Query/Statement
    }

    /**
     * Removes the given hall and all its associated screenings from the database.
     *
     * @param hallNumber the number of the hall to remove
     * @return the Hall object of the removed hall if successful, null otherwise
     */
    public Hall removeHall(String hallNumber) {
        // TODO create SQL Query/Statement
        return null;
    }
}
