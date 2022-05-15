package hslu.sweng.fs22.team2;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        String username = "cinema_admin";
        String password = "SgWm21M6gp9S";

        Management EntryHandler = new Management(username, password.toCharArray());

        Movie movieSearch = EntryHandler.searchMovieByID("33");
        System.out.println(movieSearch.getMovieName());


//        Hall hall1 = hallList.stream().filter(Hall -> "1".equals(Hall.getHallNumber())).findAny().orElse(null);
//
//        String movieID = "";
//        String movieName = "Warcraft";
//        Integer releaseYear = 1998;
//        String director = "Adolfos";
//        Duration duration = Duration.ofMinutes(0);
//
//        Movie testMovie = new Movie(movieID, movieName, releaseYear, director, duration, username, password.toCharArray());
//        System.out.println(testMovie.getMovieID());

    }
}
