package rememories.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class DateUtilTest {
    @Test
    void TodayInternally() {
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        final int month = Calendar.getInstance().get(Calendar.MONTH);
        final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        // assert
        final String today = DateUtil.getToday();
        assertTrue(today.contains(Integer.toString(year)));
        assertTrue(today.contains(Integer.toString(month)));
        assertTrue(today.contains(Integer.toString(day)));
    }

    @Test
    void fromStringNoError() {
        assertNotNull(DateUtil.fromString("2014-06-12"));
    }

    @Test
    void fromStringError() {
        final int year = 0;
        final int month = 1;
        final int day = 1;

        // assert
        final Date date = DateUtil.fromString("abc");
        assertTrue(date.toString().contains(Integer.toString(year)));
        assertTrue(date.toString().contains(Integer.toString(month)));
        assertTrue(date.toString().contains(Integer.toString(day)));
    }
}
