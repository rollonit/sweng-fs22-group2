package hslu.sweng.fs22.team2;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

    /**
     * Converts a date in string form to a local date object
     *
     * @param toConvert date in format HH:mm yyyy-MM-dd
     * @return a local date time object if valid, 1990.01.01 00:00:00 otherwise
     */
    public static LocalDateTime convertTextToDate(String toConvert) {
        try {
            return LocalDateTime.parse(toConvert, DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
            return LocalDateTime.of(1990, 1, 1, 0, 0, 0);
        }
    }

    /**
     * Converts a local date object to epoch milliseconds in Zurich time
     *
     * @param toConvert local date object to convert
     * @return the epoch milliseconds form of the given date object
     */
    public static long convertDateToMillis(LocalDateTime toConvert) {
        try {
            return toConvert.atZone(ZoneId.of("Europe/Zurich")).toInstant().toEpochMilli();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Converts a date in string form to epoch milliseconds in Zurich time
     *
     * @param toConvert date in format HH:mm yyyy-MM-dd
     * @return epoch milliseconds form of the given date
     */
    public static long convertTextToMillis(String toConvert) {
        return convertDateToMillis(convertTextToDate(toConvert));
    }

    /**
     * Converts epoch milliseconds to a LocalDateTime object in Zurich time
     *
     * @param epochMillis epoch milliseconds to convert
     * @return a Local date time object
     */
    public static LocalDateTime convertMillisToDateTime(long epochMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.of("Europe/Zurich"));
    }
}
