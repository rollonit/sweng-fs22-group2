package hslu.sweng.fs22.team2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public Date convertTextToDate(String date) throws ParseException {

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            Date dateToAdd = dateFormatter.parse(date);
            return dateToAdd;
        }
        catch (Exception e) {
            Date dateToAdd = new Date(1990, 01, 01);
            return dateToAdd;
        }
    }
}
