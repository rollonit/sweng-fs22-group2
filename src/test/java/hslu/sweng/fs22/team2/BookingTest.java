package hslu.sweng.fs22.team2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private String bookingID;
    private String screeningID;
    private String bookedSeats;
    private long bookingTime;
    private String bookingCode;


    private Booking booking;

    private DBHandler databaseHandler;

    @BeforeEach
    void setUp() throws SQLException {
        this.bookingID = "";
        this.screeningID = "2";
        this.bookedSeats = "2/2";
        this.bookingTime = 1653237693350L;
        this.bookingCode = Booking.getRandString();

        this.booking = new Booking(this.bookingID, this.screeningID, this.bookedSeats, this.bookingTime, this.bookingCode);


        databaseHandler = new DBHandler();
    }

    @Test
    void getBookingID() throws SQLException {
        String result = this.booking.getBookingID();

        assertEquals(this.booking.generateBookingID(), result);
    }

    @Test
    void getScreeningID() {
        String result = this.booking.getScreeningID();

        assertEquals(this.screeningID, result);
    }

    @Test
    void getBookedSeats() {
        String result = this.booking.getBookedSeats();

        assertEquals(this.bookedSeats, result);
    }

    @Test
    void getBookedSeatsList() {

        assertEquals((Arrays.asList(this.bookedSeats.split(";"))), this.booking.getBookedSeatsList());
    }

    @Test
    void getBookingTime() {
        long result = this.booking.getBookingTime();

        assertEquals(this.bookingTime, result);
    }

    @Test
    void getBookingCode() {
        String result = this.booking.getBookingCode();

        assertEquals(this.bookingCode, result);
    }

    @Test
    void saveBooking() throws SQLException {
        int result = this.booking.saveBooking();
        assertEquals(1,result);
        this.booking.removeBooking();
    }

    @Test
    void editBooking() throws SQLException {
        String screeningID2 = "4";
        String bookedSeats2 = "3/3";
        long bookingTime2 = 1653139547972L;
        String bookingCode2 = Booking.getRandString();

        String screeningIDfromQuery = "";
        String bookedSeatsfromQuery = "";
        long bookingTimefromQuery = 0;
        String bookingCodefromQuery = "";

        if(!this.booking.doesExist()) {
            this.booking.saveBooking();
        }

        this.booking.editBooking(screeningID2, bookedSeats2, bookingTime2, bookingCode2);

        boolean check = false;
        String queryText = String.format("SELECT * FROM booking WHERE bookingID = '%s';", this.booking.getBookingID());
        ResultSet rs = databaseHandler.query(queryText);
        while(rs.next())
        {
            screeningIDfromQuery = rs.getString("screeningID");
            bookedSeatsfromQuery = rs.getString("bookedSeats");
            bookingTimefromQuery = rs.getLong("bookingTime");
            bookingCodefromQuery = rs.getString("bookingCode");
        }

        if(screeningIDfromQuery.equals(screeningID2) &&
                bookedSeatsfromQuery.equals(bookedSeats2) &&
                bookingTimefromQuery == bookingTime2 &&
                bookingCodefromQuery.equals(bookingCode2)
        ){
            check = true;
        }

        assertEquals(true, check);

        this.booking.removeBooking();
    }

    @Test
    void removeBooking() throws SQLException {
        if(!this.booking.doesExist()) {
            this.booking.saveBooking();
        }
        this.booking.removeBooking();
        boolean check = this.booking.doesExist();
        assertEquals(false, check);
    }

    @Test
    void doesExist() throws SQLException {
        Boolean result  = this.booking.doesExist();

        assertEquals(false, result);
    }

    @Test
    void generateBookingID() throws SQLException {
        ResultSet rs = databaseHandler.query("SELECT bookingID FROM booking order by bookingID desc LIMIT 1;");
        String latestName = "";
        while (rs.next()) {
            latestName = rs.getString("bookingID");
        }

        int bookingID = Integer.parseInt(latestName);
        bookingID++;

        String shouldbe = this.booking.generateBookingID();
        assertEquals(shouldbe, (Integer.toString(bookingID)));
    }
}