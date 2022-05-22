package hslu.sweng.fs22.team2;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Backend main class that runs on the CL. Used for testing and things.
 */
public class App {

    /**
     * Entry point for the backend test CLI.
     */
    public static void main(String[] args) throws SQLException, ParseException {
        String username = "cinema_admin";
        String password = "SgWm21M6gp9S";

        Management entryHandler = new Management();
        Object searchedMovie = entryHandler.searchMovieByID("32");
        if (searchedMovie != null) {
            //System.out.println(((Movie) searchedMovie).getMovieName());
        }

        Object searchedHall = entryHandler.searchHallByID("2");
        if (searchedHall != null) {
//            Hall hall = ((Hall) searchedHall);
//            System.out.println(hall.getSeatIDList());
        }

        Object searchedScreening = entryHandler.searchScreeningByID("1");
        if (searchedScreening != null) {
//            Screening screening = ((Screening) searchedScreening);
//            System.out.println(screening.generateScreeningID());
        }

        Object searchedBooking = entryHandler.searchBookingByID("1");
        if (searchedBooking != null) {
            Booking booking = ((Booking) searchedBooking);
            System.out.println(booking.getBookedSeatsList());
        }


    }
}
