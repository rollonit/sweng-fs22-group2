package hslu.sweng.fs22.team2;

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
        DBHandler databaseHandler = new DBHandler(username, password.toCharArray());

        /**
         * Retrieves a list of all movies and creates the respective objects
         */
        ResultSet rs = databaseHandler.query("SELECT * FROM movie;");
        List<Movie> movieList = new ArrayList<Movie>(0);
        if(rs != null) {

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            movieList = new ArrayList<Movie>(columns);
            while(rs.next())
            {
                String movieID = rs.getString("movieID");
                String movieName = rs.getString("movieName");
                Integer releaseYear = rs.getInt("releaseYear");
                String director = rs.getString("director");
                Double duration = rs.getDouble("duration");

                int durationToInt = (int) Math.round(duration);
                Duration durationConverted = Duration.of((long) durationToInt, ChronoUnit.MINUTES);

//                System.out.println("MovieID: "+movieID);
//                System.out.println("Movie Name: "+movieName);
//                System.out.println("Release Year: "+releaseYear);
//                System.out.println("Director: "+director);
//                System.out.println("Duration: "+duration);

                movieList.add(new Movie(movieID, movieName, releaseYear, director, durationConverted, username, password.toCharArray()));
            }

        }



    }
}
