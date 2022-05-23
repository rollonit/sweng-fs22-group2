package hslu.sweng.fs22.team2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ScreeningTest {

    private String screeningID;
    private String hallNumber;
    private String movieID;
    private long screeningTime;

    private Screening screening;
    private DBHandler databaseHandler;

    @BeforeEach
    void setUp() throws SQLException {
        this.screeningID = "";
        this.hallNumber = "1";
        this.movieID = "1";
        this.screeningTime = 1653306600000L;

        this.screening = new Screening(this.screeningID,this.movieID, this.hallNumber, this.screeningTime);

        databaseHandler = new DBHandler();
    }

    @Test
    void getScreeningID() throws SQLException {
        String result = this.screening.getScreeningID();

        assertEquals(this.screening.generateScreeningID(), result);
    }

    @Test
    void getMovieID() {
        String result = this.screening.getMovieID();

        assertEquals(this.movieID, result);
    }

    @Test
    void getHallNumber() {
        String result = this.screening.getHallNumber();

        assertEquals(this.hallNumber, result);
    }

    @Test
    void getScreeningTime() {
        long result = this.screening.getScreeningTime();

        assertEquals(this.screeningTime, result);
    }

    @Test
    void saveScreening() throws SQLException {
        int result = this.screening.saveScreening();
        assertEquals(1,result);
        this.screening.removeScreening();
    }

    @Test
    void editScreening() throws SQLException {
        String hallNumber2 = "2";
        String movieID2 = "2";
        long screeningTime2 = 1653306600001L;

        String hallNumberFromQuery = "";
        String movieIDFromQuery = "";
        long screeningTimeFromQuery = 0;


        if(!this.screening.doesExist()) {
            this.screening.saveScreening();
        }

        this.screening.editScreening(screeningTime2, hallNumber2, movieID2);

        boolean check = false;
        String queryText = String.format("SELECT * FROM screening WHERE screeningID = '%s';", this.screening.getScreeningID());
        ResultSet rs = databaseHandler.query(queryText);
        while(rs.next())
        {
            hallNumberFromQuery = rs.getString("hallNumber");
            movieIDFromQuery = rs.getString("movieID");
            screeningTimeFromQuery = rs.getLong("screeningTime");
        }


        if(hallNumberFromQuery.equals(hallNumber2) &&
                movieIDFromQuery.equals(movieID2) &&
                screeningTimeFromQuery == screeningTime2
        ){
            check = true;
        }

        assertEquals(true, check);

        this.screening.removeScreening();
    }

    @Test
    void removeScreening() throws SQLException {
        if(!this.screening.doesExist()) {
            this.screening.saveScreening();
        }
        this.screening.removeScreening();
        boolean check = this.screening.doesExist();
        assertEquals(false, check);
    }

    @Test
    void doesExist() throws SQLException {
        Boolean result  = this.screening.doesExist();

        assertEquals(false, result);
    }

    @Test
    void generateScreeningID() throws SQLException {
        ResultSet rs = databaseHandler.query("SELECT screeningID FROM screening order by screeningID desc LIMIT 1;");
        String latestName = "";
        while (rs.next()) {
            latestName = rs.getString("screeningID");
        }

        int screeningID = Integer.parseInt(latestName);
        screeningID++;

        String shouldbe = this.screening.generateScreeningID();
        assertEquals(shouldbe, (Integer.toString(screeningID)));
    }
}