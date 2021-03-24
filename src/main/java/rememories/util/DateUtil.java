package rememories.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Utility class.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private DateUtil() {
    }

    public static String getToday() {
        return getFormatter().format(Calendar.getInstance().getTime());
    }

    public static Date fromString(String dateString) {
        try {
            return getFormatter().parse(dateString);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    public static SimpleDateFormat getFormatter() {
        return new SimpleDateFormat(DATE_FORMAT);
    }

    public static Comparator<String> getComparator() {
        return Comparator.comparing(DateUtil::fromString);
    }
}
