package hslu.sweng.fs22.team2;

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
     * Searches in screeningList for hall with ID
     * @param screeningID ID of the screening to search for
     */
    public Object searchScreeningByID(String screeningID){
        Screening screeningToReturn = screeningList.stream().filter(Screening -> screeningID.equals(Screening.getScreeningID())).findAny().orElse(null);
        return screeningToReturn;
    }
}
