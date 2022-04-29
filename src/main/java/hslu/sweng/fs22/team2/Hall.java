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
     */
    public Hall(String hallNumber, int hallWidth, int hallLength) {
        this.hallNumber = hallNumber;
        this.hallWidth = hallWidth;
        this.hallLength = hallLength;
    }

    /**
     * Returns the Hall object with the given number.
     *
     * @param hallNumber the unique hall number
     * @return the Hall object if found, null otherwise
     */
    public Hall getHall(String hallNumber) {
        // TODO create SQL Query/Statement
        return null;
    }

    /**
     * Creates a new hall in the database with the given parameters.
     *
     * @param hallNumber a unique new hall number to assign to the hall
     * @param hallWidth  the number of seats in each row of the hall
     * @param hallLength the number of rows of seats in the hall
     */
    public void createHall(String hallNumber, String hallWidth, String hallLength) {
        // TODO create SQL Query/Statement
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
