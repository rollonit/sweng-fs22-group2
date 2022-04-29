package hslu.sweng.fs22.team2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a single booking with one or more seats for a screening.
 */
public class Booking {

    /**
     * Stores the seats associated with this booking.
     */
    private final ArrayList<Seat> seatList;
    /**
     * Stores unique booking ID for use as a primary key in the DB.
     */
    private String bookingID;
    /**
     * Stores the associated screening ID for this booking for use as a foreign key.
     */
    private String screeningID;
    /**
     * Time at which the booking was made.
     */
    private Date bookingTime;
    /**
     * Unique ID/QR Code used to authenticate the booking before a screening.
     */
    private String bookingCode;
    /**
     * Stores the total amount for the transaction. MIGHT REPLACE WITH CODE TO CALCULATE IT SPONTANEOUSLY BUT WILL HAVE TO WORK OUT DISCOUNTS SEPARATELY.
     */
    private double transactionAmount;

    /**
     * Default constructor for the Booking class.
     */
    public Booking() {
        this.seatList = new ArrayList<>();
        transactionAmount = 0.0d;
    }

    /**
     * Parametrised constructor for the Booking class. TODO NEEDS MORE INFO FOR SEATS.
     *
     * @param seatList          the list of seats involved in the booking TODO might wanna replace with list of strings which are fetched later
     * @param screeningID       screening ID for which the booking is made
     * @param transactionAmount amount for the transaction MIGHT CHANGE
     * @param bookingTime       time at which the booking is made
     */
    public Booking(ArrayList<Seat> seatList, String screeningID, double transactionAmount, Date bookingTime) {
        this.seatList = seatList;
        this.screeningID = screeningID;
        this.transactionAmount = transactionAmount;
        this.bookingTime = bookingTime;
    }

    /**
     * Created a new booking with the supplied data.
     */
    public void createBooking(ArrayList<Seat> seatList, String screeningID) {
        // TODO create SQL Query/Statement
        // TODO random generator for bookingCode
        // TODO Time and date code to fetch current date and time
    }

    /**
     * Gets a specific booking from the DB based on booking ID.
     *
     * @param bookingID the booking ID to fetch
     */
    public Booking getBooking(String bookingID) {
        // TODO create SQL Query/Statement
        return null;
    }
}
