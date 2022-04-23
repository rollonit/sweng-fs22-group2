package hslu.sweng.fs22.team2;

import java.util.ArrayList;

/**
 * This class is used to manage the data and bookings from the perspective of an employee at the counter.
 * <i>(Currently full of bugs because most of its classes haven't been implemented yet)</i>
 * TODO figure out setters and getters and if the info is stored constantly or only fetched when requested
 */
public class Management {

    /**
     * Stores all the current screenings in the theater.
     */
    private final ArrayList<Screening> screeningList;
    /**
     * Stores all the movies currently playing at the theater.
     */
    private final ArrayList<Movie> movieList;
    /**
     * Stores all the bookings made at the theater.
     */
    private final ArrayList<Booking> bookingList;

    /**
     * Default constructor for the Management class. Does not initialise anything.
     */
    public Management() {
        screeningList = new ArrayList<>();
        movieList = new ArrayList<>();
        bookingList = new ArrayList<>();
    }
}
