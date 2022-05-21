package hslu.sweng.fs22.team2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Helper {
    public static int countOccurrences(char delimiter, String inputString) {
        int count = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == delimiter) {
                count++;
            }
        }

        return count;
    }

    public static Date convertTextToDate(String date) throws ParseException {

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
            Date dateToAdd = dateFormatter.parse(date);
            return dateToAdd;
        } catch (Exception e) {
            Date dateToAdd = new Date(1990, 01, 01);
            return dateToAdd;
        }
    }

    public static long convertDateToTicks(Date date) {
        try {
            long ticksAtEpoch = 621355968000000000L;
            long ticksPerMillisecond = 10000;

            TimeZone timeZone = TimeZone.getDefault();
            long offSet = timeZone.getOffset(date.getTime());

            long givenTimeMillis = (date.getTime() + offSet) * ticksPerMillisecond;

            return (givenTimeMillis + ticksAtEpoch);

        } catch (Exception e) {

            return (long) 0;

        }
    }

    public static Date convertTicksToDate(long ticks) {
        long ticksAtEpoch = 621355968000000000L;
        long ticksPerMillisecond = 10000;

        return new Date((ticks - ticksAtEpoch) / ticksPerMillisecond);
    }
}
