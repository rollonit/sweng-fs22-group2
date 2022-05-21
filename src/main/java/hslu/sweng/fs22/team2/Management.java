package hslu.sweng.fs22.team2;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used to manage the data and bookings from the perspective of an employee at the counter.
 * <i>(Currently full of bugs because most of its classes haven't been implemented yet)</i>
 * TODO figure out setters and getters and if the info is stored constantly or only fetched when requested
 */
public class Management {

    /**
     * Stores all the current screenings in the theater.
     */
    private List<Screening> screeningList;
    /**
     * Stores all the movies currently playing at the theater.
     */
    private List<Movie> movieList;
    /**
     * Stores all the bookings made at the theater.
     */
    private List<Booking> bookingList;

    /**
     * Stores all the Halls at the theater.
     */
    private List<Hall> hallList;

    /**
     * DBHandler for SQL Queries
     */
    private DBHandler databaseHandler;

    /**
     * Stores all the Halls at the theater.
     */
    private Helper helper;

    /**
     * Default constructor for the Management class.
     */
    public Management() throws SQLException, ParseException {
        screeningList = new ArrayList<Screening>();
        movieList = new ArrayList<Movie>();
        hallList  = new ArrayList<Hall>();
        bookingList = new ArrayList<Booking>();

        DBHandler databaseHandler = new DBHandler();
        helper = new Helper();



        // Retrieves a list of all movies and creates the respective objects
        ResultSet rs = databaseHandler.query("SELECT * FROM movie;");
        if(rs != null) {

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            this.movieList = new ArrayList<Movie>(columns);
            while(rs.next())
            {
                String movieID = rs.getString("movieID");
                String movieName = rs.getString("movieName");
                int releaseYear = rs.getInt("releaseYear");
                String director = rs.getString("director");
                double duration = rs.getDouble("duration");

                int durationToInt = (int) Math.round(duration);
                Duration durationConverted = Duration.of((long) durationToInt, ChronoUnit.MINUTES);

//                System.out.println("MovieID: "+movieID);
//                System.out.println("Movie Name: "+movieName);
//                System.out.println("Release Year: "+releaseYear);
//                System.out.println("Director: "+director);
//                System.out.println("Duration: "+duration);

                this.movieList.add(new Movie(movieID, movieName, releaseYear, director, durationConverted));
            }
        }



        // Retrieves a list of all Halls and creates the respective objects
        double normalPrice = 10.00;
        double lastRowPrice = 15.00;
        rs = databaseHandler.query("SELECT * FROM hall;");
        if(rs != null) {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            this.hallList = new ArrayList<Hall>(columns);

            while(rs.next())
            {
                String hallNumber = rs.getString("hallNumber");
                int hallWidth = rs.getInt("hallWidth");
                int hallLength = rs.getInt("hallLength");


                this.hallList.add(new Hall(hallNumber, hallWidth, hallLength, normalPrice, lastRowPrice));

            }

        }



        // Retrieves a list of all Screenings and creates the respective objects
        rs = databaseHandler.query("SELECT * FROM screening;");
        if(rs != null) {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            this.screeningList = new ArrayList<Screening>(columns);

            while(rs.next())
            {
                String screeningID = rs.getString("screeningID");
                String movieID = rs.getString("movieID");
                String hallNumber = rs.getString("hallNumber");
                long screeningTime = rs.getLong("screeningTime");

                this.screeningList.add(new Screening(screeningID, movieID, hallNumber, screeningTime));
            }

        }



        // Retrieves a list of all Bookings and creates the respective objects
        rs = databaseHandler.query("SELECT * FROM booking;");
        if(rs != null) {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            this.bookingList = new ArrayList<Booking>(columns);

            while(rs.next())
            {
                String bookingID = rs.getString("bookingID");
                String screeningID = rs.getString("screeningID");
                String bookedSeats = rs.getString("bookedSeats");
                long bookedTime = rs.getLong("bookingTime");
                String bookingCode = rs.getString("bookingCode");


                this.bookingList.add(new Booking(bookingID, screeningID, bookedSeats, bookedTime, bookingCode));

            }

        }
    }


    /**
     * Returns movieList
     */
    public List<Movie> getMovieList() {
        return this.movieList;
    }

    /**
     * Searches in Movielist for movie with ID
     * @param movieID ID of the movie to search for
     */
    public Object searchMovieByID(String movieID){
        Movie movieToReturn = movieList.stream().filter(Movie -> movieID.equals(Movie.getMovieID())).findAny().orElse(null);
        return movieToReturn;
    }



    /**
     * Returns hallList
     */
    public List<Hall> getHallList(){
        return this.hallList;
    }

    /**
     * Searches in hallList for hall with ID
     * @param hallNumber ID of the hall to search for
     */
    public Object searchHallByID(String hallNumber){
        Hall hallToReturn = hallList.stream().filter(Hall -> hallNumber.equals(Hall.getHallNumber())).findAny().orElse(null);
        return hallToReturn;
    }



    /**
     * Returns screeningList
     */
    public List<Screening> getScreeningList(){
        return this.screeningList;
    }

    /**
     * Searches in screeningList for screening with ID
     * @param screeningID ID of the screening to search for
     */
    public Object searchScreeningByID(String screeningID){
        Screening screeningToReturn = screeningList.stream().filter(Screening -> screeningID.equals(Screening.getScreeningID())).findAny().orElse(null);
        return screeningToReturn;
    }



    /**
     * Returns bookingList
     */
    public List<Booking> getBookingList(){
        return this.bookingList;
    }

    /**
     * Searches in bookingList for booking with ID
     * @param bookingID ID of the booking to search for
     */
    public Object searchBookingByID(String bookingID){
        Booking bookingToReturn = bookingList.stream().filter(Booking -> bookingID.equals(Booking.getBookingID())).findAny().orElse(null);
        return bookingToReturn;
    }

    /**
     * Searches in bookingList for booking with ScreeningID
     * @param screeningID ID of the booking to search for
     */
    public List<Booking> searchBookingsByScreeningID(String screeningID){
        List<Booking> bookingListSorted = new ArrayList<Booking>(0);
        for(Booking booking : getBookingList()){
            if(booking.getScreeningID().equals(screeningID)){
                bookingListSorted.add(booking);
            }
        }
        return bookingListSorted;
    }

    /**
     * Validates seats of booking
     */
    public boolean validateBookedSeats(Booking booking){

        boolean isValid = false;

        List<String> bookedSeatsList = booking.getBookedSeatsList();
        try {
            Object screening = searchScreeningByID(booking.getScreeningID());
            if(screening != null) {
                String hallNumber = ((Screening) screening).gethallNumber();

                Object hall = searchHallByID(hallNumber);
                if(hall != null) {
                    List<String> seatIDList = ((Hall) hall).getSeatIDList();

                    isValid = seatIDList.containsAll(bookedSeatsList);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to find Seatlist from screening --> hall");
        }

        return isValid;
    }

    /**
     * Gets available Seats of Screening
     */
    public List<String> getAvailableSeatIDs(Screening screening){

        List<String> availableSeatIDsList = new ArrayList<String>(0);

        List<Booking> sortedBookings = searchBookingsByScreeningID(screening.getScreeningID());
        List<String> bookedSeats = new ArrayList<String>(0);
        for(Booking booking : sortedBookings){
            for(String bookedSeatID : booking.getBookedSeatsList())
            {
                bookedSeats.add(bookedSeatID);
            }
        }

        Object searchedHall = searchHallByID(screening.gethallNumber());
        if(searchedHall != null) {
            List<String> seatIDList = ((Hall) searchedHall).getSeatIDList();
            for(String seatID : seatIDList){
                if(!bookedSeats.contains(seatID)){
                    availableSeatIDsList.add(seatID);
                }
            }
        }

        return availableSeatIDsList;
    }
}
