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
     * Stores the related Hall
     */
    private String hallNumber;

    /**
     * Default constructor for the Seat class.
     */
    public Seat() {
        this.price = 0.0d;
    }

    /**
     * Parametrised constructor for the Seat class.
     *
     * @param seatID     the unique seatID / Hall
     * @param x          the seat number in the row
     * @param y          the row letter
     * @param price      the price for that seat
     * @param hallNumber unique HallNumber
     */
    public Seat(String seatID, String x, String y, double price, String hallNumber) {
        this.seatID = seatID;
        this.x = x;
        this.y = y;
        this.price = price;
        this.hallNumber = hallNumber;
    }


    /**
     * Sets price of the seat in the Object and the Database
     */
    public void setPrice() {

    }

    /**
     * Returns the unique seatID.
     */
    public String getSeatID() {
        String seatIDFormated = "";
        seatIDFormated = this.seatID.replaceAll("^\\d+_", "");
        return seatIDFormated;
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
