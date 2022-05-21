package hslu.sweng.fs22.team2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HallTest {

    private String hallNumber;
    private int hallWidth;
    private int hallLength;
    private double normalPrice;
    private double lastRowPrice;

    private Hall hall;
    private DBHandler databaseHandler;

    @BeforeEach
    void setUp() throws SQLException {
        this.hallNumber = "";
        this.hallWidth = 10;
        this.hallLength = 10;
        this.normalPrice = 10.00;
        this.lastRowPrice = 15.00;

        this.hall = new Hall(this.hallNumber, this.hallWidth, this.hallLength, this.normalPrice, this.lastRowPrice);

        databaseHandler = new DBHandler();
    }

    @Test
    void getHallNumber() throws SQLException {
        String result = this.hall.getHallNumber();

        assertEquals(this.hall.generateHallNumber(), result);
    }

    @Test
    void getHallWidth() {
        int result = this.hall.getHallWidth();

        assertEquals(this.hallWidth, result);
    }

    @Test
    void getHallLength() {
        int result = this.hall.getHallLength();

        assertEquals(this.hallLength, result);
    }

    @Test
    void getNormalPrice() {
        double result = this.hall.getNormalPrice();

        assertEquals(this.normalPrice, result);
    }

    @Test
    void getLastRowPrice() {
        double result = this.hall.getLastRowPrice();

        assertEquals(this.lastRowPrice, result);
    }

    @Test
    void getSeatList() throws SQLException {
        this.hall.saveHall();
        boolean check = false;

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

        if(this.hall.getSeatList().containsAll(seatList)){
            check = true;
        }

        assertEquals(true, check);
        this.hall.removeHall();
    }

    @Test
    void getSeatIDList() throws SQLException {
        this.hall.saveHall();
        boolean check = false;
        List<String>seatIDList = new ArrayList<>();

        String queryText = String.format("select * from seat WHERE hallNumber = '%s';", this.hall.getHallNumber());
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


                seatList.add(new Seat(seatID, x, y, price, this.hall.getHallNumber()));
            }
        }


        for(Seat seat : seatList){
            seatIDList.add(seat.getSeatID());
        }

        if(this.hall.getSeatIDList().containsAll(seatIDList)){
            check = true;
        }

        assertEquals(true, check);

        this.hall.removeHall();
    }

    @Test
    void saveHall() throws SQLException {
        int result = this.hall.saveHall();
        assertEquals(1,result);
        this.hall.removeHall();
    }

    @Test
    void editHall() throws SQLException {
        int hallWidth2 = 11;
        int hallLength2 = 11;
        double normalPrice2 = 11.00;
        double lastRowPrice2 = 12.00;

        int hallWidthfromQuery = 0;
        int hallLengthfromQuery = 0;


        if(!this.hall.doesExist()) {
            this.hall.saveHall();
        }

        this.hall.editHall(hallWidth2, hallLength2, normalPrice2, lastRowPrice2);

        boolean check = false;
        String queryText = String.format("SELECT * FROM hall WHERE hallNumber = '%s';", this.hall.getHallNumber());
        ResultSet rs = databaseHandler.query(queryText);
        while(rs.next())
        {
            hallWidthfromQuery = rs.getInt("hallWidth");
            hallLengthfromQuery = rs.getInt("hallLength");
        }


        if(hallWidthfromQuery == hallWidth2 &&
                hallLengthfromQuery == hallLength2
        ){
            check = true;
        }

        assertEquals(true, check);

        this.hall.removeHall();
    }

    @Test
    void removeHall() throws SQLException {
        if(!this.hall.doesExist()) {
            this.hall.saveHall();
        }
        this.hall.removeHall();
        boolean check = this.hall.doesExist();
        assertEquals(false, check);
    }

    @Test
    void getSeatInfo() throws SQLException {
        if(!this.hall.doesExist()) {
            this.hall.saveHall();
        }

        String seatID = "1/1";
        String seatIDSQL = (this.hallNumber + "_1/1");



        this.hall.getSeatInfo(seatID);

        String queryText = String.format("SELECT * FROM seat WHERE seatID = '%s';", seatIDSQL);
        ResultSet rs = databaseHandler.query(queryText);
        while(rs.next()) {
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            double price = rs.getDouble("price");
        }







        this.hall.removeHall();
    }

    @Test
    void checkSeats() {
    }

    @Test
    void doesExist() {
    }

    @Test
    void generateHallNumber() {
    }
}