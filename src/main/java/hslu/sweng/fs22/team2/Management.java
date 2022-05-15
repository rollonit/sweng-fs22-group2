package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
     * Default constructor for the Management class. Does not initialise anything.
     */
    public Management(String username, char[] password) throws SQLException {
        screeningList = new ArrayList<Screening>();
        movieList = new ArrayList<Movie>();
        hallList  = new ArrayList<Hall>();
        bookingList = new ArrayList<Booking>();

        DBHandler databaseHandler = new DBHandler(username, password);
        /**
         * Retrieves a list of all movies and creates the respective objects
         */
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

                movieList.add(new Movie(movieID, movieName, releaseYear, director, durationConverted, username, password));
            }
        }

        /**
         * Retrieves a list of all Halls and creates the respective objects
         */
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


                hallList.add(new Hall(hallNumber, hallWidth, hallLength, username, password));
            }

        }
    }

    public Movie searchMovieByID(String movieID){
        Movie movieToReturn = movieList.stream().filter(Movie -> movieID.equals(Movie.getMovieID())).findAny().orElse(null);
        return movieToReturn;
    }
}
