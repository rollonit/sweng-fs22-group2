package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * The number of rows in the theater.
     */
    private double normalPrice;

    /**
     * The number of rows in the theater.
     */
    private double lastRowPrice;

    /**
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

    /**
     * SeatList for Hall
     */
    private List<Seat> seatList = new ArrayList<Seat>(0);

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
     * @param hallNumber   the number to assign to hall
     * @param hallWidth    the number of seats in each row of the hall
     * @param hallLength   the number of rows of seats in the hall
     * @param normalPrice  the normal price for a seat in the hall
     * @param lastRowPrice the increased premium price for a last row seat
     */
    public Hall(String hallNumber, int hallWidth, int hallLength, double normalPrice, double lastRowPrice) throws SQLException {
        this.hallNumber = hallNumber;
        this.hallWidth = hallWidth;
        this.hallLength = hallLength;

        this.normalPrice = normalPrice;
        this.lastRowPrice = lastRowPrice;

        this.databaseHandler = new DBHandler();

        this.seatList = getSeats();

        if (this.hallNumber.isEmpty()) {

            this.hallNumber = (generateHallNumber());
        }
    }

    /**
     * @return The Hall Number.
     */
    public String getHallNumber() {
        return this.hallNumber;
    }

    /**
     * @return The Hall Width.
     */
    public int getHallWidth() {
        return this.hallWidth;
    }

    /**
     * @return The Hall Length.
     */
    public int getHallLength() {
        return this.hallLength;
    }

    /**
     * @return The normal price of a seat
     */
    public double getNormalPrice() {
        return this.normalPrice;
    }

    /**
     * @return The normal price of a seat
     */
    public double getLastRowPrice() {
        return this.lastRowPrice;
    }

    /**
     * @return List of seats
     */
    public List<Seat> getSeatList() {
        return this.seatList;
    }

    /**
     * @return List of seatIDs
     */
    public List<String> getSeatIDList() {
        List<String> seatIDList = new ArrayList<String>((getSeatList()).size());
        for (Seat seat : (getSeatList())) {
            seatIDList.add(seat.getSeatID());
        }
        return seatIDList;
    }


    /**
     * Saves the hall object to the Database
     */
    public int saveHall() throws SQLException {
        if (!(doesExist())) {
            String queryText = String.format("INSERT INTO hall (hallNumber, hallWidth, hallLength) " +
                            "VALUE ('%s', '%s', '%s');",
                    this.hallNumber, this.hallWidth, this.hallLength);
            ResultSet rs = this.databaseHandler.query(queryText);

            updateSeats(this.normalPrice, this.lastRowPrice);
            return 1;
        } else {
            System.out.println("HallNumber Already in Database");
            return -1;
        }
    }

    /**
     * Edits the Hall with the given parameters
     * Changes the object itself and write those changes to the Database as well
     *
     * @param hallWidth    the number of seats in each row of the hall
     * @param hallLength   the number of rows of seats in the hall
     * @param normalPrice  the price for normal seats
     * @param lastRowPrice the price for seats in the last row
     */
    public void editHall(Integer hallWidth, Integer hallLength, double normalPrice, double lastRowPrice) throws SQLException {
        this.hallWidth = hallWidth;
        this.hallLength = hallLength;

        if (doesExist()) {
            String queryText = String.format("UPDATE hall SET " +
                    "hallWidth = '%s', " +
                    "hallLength = '%s' " +
                    "WHERE hallNumber = '%s';", hallWidth, hallLength, this.hallNumber);
            this.databaseHandler.query(queryText);

            updateSeats(normalPrice, lastRowPrice);
            this.seatList.clear();
            this.seatList = getSeats();
        }
    }

    /**
     * Removes the hall from the Database
     * Removes all related seats from the Database
     */
    public void removeHall() throws SQLException {
        if (doesExist()) {
            String queryText = String.format("DELETE FROM hall WHERE hallNumber = '%s'", this.hallNumber);
            this.databaseHandler.query(queryText);

            removeSeats();
        }
    }


    /**
     * Gets all Seats for the Hall
     */
    private List<Seat> getSeats() throws SQLException {
        String queryText = String.format("select * from seat WHERE hallNumber = '%s';", this.hallNumber);
        ResultSet rs = this.databaseHandler.query(queryText);
        List<Seat> seatList = new ArrayList<Seat>(0);

        if (rs != null) {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            seatList = new ArrayList<Seat>(columns);
            while (rs.next()) {

                String seatID = rs.getString("seatID");
                String x = rs.getString("x");
                String y = rs.getString("y");
                double price = rs.getDouble("price");

                seatList.add(new Seat(seatID, x, y, price, this.hallNumber));
            }
        }

        return seatList;

    }

    /**
     * Gets all Seats for the Hall
     */
    public Seat getSeatInfo(String seatID) throws SQLException {

        return seatList.stream().filter(Seat -> seatID.equals(Seat.getSeatID())).findAny().orElse(null);

    }

    /**
     * checks amount of Seats
     */
    public Boolean checkSeats() throws SQLException {
        return this.seatList.size() == (this.hallWidth * this.hallLength);
    }

    /**
     * Removes all Seats and creates new seats according to the Length and with of the hall
     *
     * @param lastRowPrice Prices for the last row
     * @param normalPrice  Prices for the other rows
     */
    private void updateSeats(double normalPrice, double lastRowPrice) throws SQLException {
        String queryText = String.format("DELETE FROM seat WHERE hallNumber = '%s'", this.hallNumber);
        this.databaseHandler.query(queryText);

        for (int y = 1; y < (this.hallLength + 1); y++) {
            for (int x = 1; x < (this.hallWidth + 1); x++) {

//                System.out.println("Column: " + y.toString() + "    Row: " + x.toString());
//                System.out.println("seatID: " + ("'" + x.toString() + "/" + y.toString() + "'"));
//                System.out.println("x: " + x.toString());
//                System.out.println("y: " + y.toString());
//                System.out.println("price: " + normalPrice);
//                System.out.println("hallNumber: " + this.hallNumber);

                double priceToSet = 0;
                if (y == hallLength) {
                    priceToSet = lastRowPrice;
                } else {
                    priceToSet = normalPrice;
                }

                queryText = String.format("INSERT INTO seat (seatID, x, y, price, hallNumber) VALUE (%s, %s, %s, %s, %s)",
                        ("'" + this.hallNumber + "_" + Integer.toString(x) + "/" + Integer.toString(y) + "'"),
                        x, y, priceToSet, this.hallNumber);

                this.databaseHandler.query(queryText);
            }
        }
    }

    /**
     * Removes all Seats for a Hall in the database
     * Clears seatList
     */
    private void removeSeats() {
        String queryText = String.format("DELETE FROM seat WHERE hallNumber = '%s'", this.hallNumber);
        this.databaseHandler.query(queryText);

        this.seatList.clear();
    }


    /**
     * Checks if the hall exists in the Database
     */
    public boolean doesExist() throws SQLException {
        int count = 0;
        boolean exists = false;

        String queryText = String.format("select COUNT(*) from hall WHERE hallNumber = '%s';", this.hallNumber);
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
     * Generates new Halnumber by checking the database
     */
    public String generateHallNumber() throws SQLException {

        ResultSet rs = databaseHandler.query("SELECT hallNumber FROM hall order by hallNumber desc LIMIT 1;");
        String latestName = "";
        while (rs.next()) {
            latestName = rs.getString("hallNumber");
        }

        int hallNumber = Integer.parseInt(latestName);
        hallNumber++;
        return Integer.toString(hallNumber);
    }
}