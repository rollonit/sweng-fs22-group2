package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents a single booking with one or more seats for a screening.
 */
public class Booking {

    /**
     * Stores unique booking ID for use as a primary key in the DB.
     */
    private String bookingID;

    /**
     * Stores the associated screening ID for this booking for use as a foreign key.
     */
    private String screeningID;

    /**
     * Stores the seats associated with this booking.
     */
    private String bookedSeats;

    /**
     * Time at which the booking was made.
     */
    private long bookingTime;

    /**
     * Unique ID/QR Code used to authenticate the booking before a screening.
     */
    private String bookingCode;

    /**
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

    /**
     * Default constructor for the Booking class.
     */
    public Booking() {
    }

    /**
     * Parametrised constructor for the Booking class. TODO NEEDS MORE INFO FOR SEATS.
     *
     * @param bookingID   ID of the booking
     * @param screeningID screening ID for which the booking is made
     * @param bookedSeats String of seats that were booked
     * @param bookingTime time at which the booking is made
     * @param bookingCode A randomly generated booking code for the purpose of authentication
     */
    public Booking(String bookingID, String screeningID, String bookedSeats, long bookingTime, String bookingCode) throws SQLException {
        this.bookingID = bookingID;
        this.screeningID = screeningID;
        this.bookedSeats = bookedSeats;
        this.bookingTime = bookingTime;
        this.bookingCode = this.getRandString();
        this.databaseHandler = new DBHandler();
    }

    /**
     * Creates a random 8 digit code for use as a booking code.
     *
     * @return the random code
     */
    private String getRandString() {
        String charsToUse = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * charsToUse.length());
            salt.append(charsToUse.charAt(index));
        }
        return salt.toString();

    }

    /**
     * Returns bookingID
     */
    public String getBookingID() {
        return this.bookingID;
    }

    /**
     * Returns screeningID
     */
    public String getScreeningID() {
        return this.screeningID;
    }

    /**
     * Returns bookedSeats
     */
    public String getBookedSeats() {
        return this.bookedSeats;
    }

    public List<String> getBookedSeatsList() {
        List<String> bookedSeatsList = Arrays.asList(this.bookedSeats.split(";"));
        return bookedSeatsList;
    }

    /**
     * Returns bookingTime
     */
    public long getBookingTime() {
        return this.bookingTime;
    }

    /**
     * Returns bookingCode
     */
    public String getBookingCode() {
        return this.bookingCode;
    }


    /**
     * Saves the booking object to the Database
     */
    public int saveBooking() throws SQLException {
        int returnCode = 0;

        if (!(doesExist())) {
            String queryText = String.format("insert into booking " +
                            "(bookingID, screeningID, bookedSeats, bookingTime, bookingCode) values " +
                            "('%s', '%s', '%s', '%s', '%s');",
                    this.bookingID, this.screeningID, this.bookedSeats, this.bookingTime, this.bookingCode);
            ResultSet rs = this.databaseHandler.query(queryText);
            returnCode = 1;
        } else {
            System.out.println("BookingID Already in Database");
            returnCode = -2;
        }
        return returnCode;
    }

    /**
     * Edits the booking with the given parameters
     * Changes the object itself and write those changes to the Database as well
     *
     * @param screeningID screening ID for which the booking is made
     * @param bookedSeats String of seats that were booked
     * @param bookingTime time at which the booking is made
     * @param bookingCode [???]
     */
    public void editBooking(String screeningID, String bookedSeats, long bookingTime, String bookingCode) throws SQLException {
        this.screeningID = screeningID;
        this.bookedSeats = bookedSeats;
        this.bookingTime = bookingTime;
        this.bookingCode = bookingCode;

        if (doesExist()) {
            String queryText = String.format("UPDATE booking SET " +
                    "screeningID = '%s', " +
                    "bookedSeats = '%s', " +
                    "bookingTime = '%s', " +
                    "bookingCode = '%s' " +
                    "WHERE bookingID = '%s';", screeningID, bookedSeats, bookingTime, bookingCode, this.bookingID);
            this.databaseHandler.query(queryText);
        }
    }

    /**
     * Removes the booking from the Database
     */
    public void removeBooking() throws SQLException {
        if (doesExist()) {
            String queryText = String.format("DELETE FROM booking WHERE bookingID = '%s'", this.bookingID);
            this.databaseHandler.query(queryText);
        }
    }


    /**
     * Checks if the Booking exists in the Database
     */
    public boolean doesExist() throws SQLException {
        int count = 0;
        boolean exists = false;

        String queryText = String.format("select COUNT(*) from booking WHERE bookingID = '%s';", this.bookingID);
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
