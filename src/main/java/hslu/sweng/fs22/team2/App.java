package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Hello world!
 */


public class App {

    public static void main(String[] args) throws SQLException, ParseException {
        String username = "cinema_admin";
        String password = "SgWm21M6gp9S";

        Management entryHandler = new Management();
        Object searchedMovie = entryHandler.searchMovieByID("32");
        if(searchedMovie != null) {
            //System.out.println(((Movie) searchedMovie).getMovieName());
        }

        Object searchedHall = entryHandler.searchHallByID("2");
        if(searchedHall != null) {
//            Hall hall = ((Hall) searchedHall);
//            hall.editHall(hall.getHallWidth(), hall.getHallLength(), hall.getNormalPrice(), hall.getLastRowPrice());
        }

        Object searchedScreening = entryHandler.searchScreeningByID("1");
        if(searchedScreening != null) {
            //System.out.println(((Screening) searchedScreening).getdateTime());
//            System.out.println(entryHandler.getAvailableSeatIDs((Screening) searchedScreening));
        }

        Object searchedBooking = entryHandler.searchBookingByID("1");
        if(searchedBooking != null) {
            double bookingPrice = entryHandler.getBookingPrice((Booking) searchedBooking);
            System.out.println(bookingPrice);
        }


    }
}
