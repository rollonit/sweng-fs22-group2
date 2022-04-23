package hslu.sweng.fs22.team2;

/**
 * Represents a single seat at a hall.
 */
public class Seat {
    /**
     * Stores unique seat ID for use as a primary key in the DB.
     */
    private String seatID;
    /**
     * Stores the seat number in its particular row.
     */
    private String x;
    /**
     * Stores the row letter of the seat at the theater.
     */
    private String y;
    /**
     * Price for the particular seat.
     */
    private double price;

    /**
     * Default constructor for the Seat class.
     */
    public Seat() {
        this.price = 0.0d;
    }

    /**
     * Parametrised constructor for the Seat class.
     *
     * @param x      the seat number in the row
     * @param y      the row letter
     * @param seatID the unique seatID
     * @param price  the price for that seat
     */
    public Seat(String x, String y, String seatID, double price) {
        this.x = x;
        this.y = y;
        this.seatID = seatID;
        this.price = price;
    }

    public void setPrice(String seatID, double price) {
        // TODO create SQL Query/Statement
    }

    /**
     * Returns the unique seatID.
     */
    public String getSeatID() {
        return this.seatID;
    }

    /**
     * Returns the seat number in the row.
     */
    public String getX() {
        return this.x;
    }

    /**
     * Returns the row letter.
     */
    public String getY() {
        return this.y;
    }

    /**
     * Returns the price.
     */
    public double getPrice() {
        return this.price;
    }
}
