package hslu.sweng.fs22.team2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

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

        Movie movie = entryHandler.getMovieList().get(0);
        System.out.println(movie.generateMovieID());

    }
}
