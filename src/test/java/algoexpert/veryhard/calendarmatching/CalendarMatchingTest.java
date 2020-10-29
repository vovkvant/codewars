package algoexpert.veryhard.calendarmatching;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CalendarMatchingTest {

    @Test
    public void TestCase1() {
        List<CalendarMatching.StringMeeting> calendar1 = Arrays.asList(new CalendarMatching.StringMeeting[]{
                new CalendarMatching.StringMeeting("9:00", "10:30"),
                new CalendarMatching.StringMeeting("16:00", "18:00"),
                new CalendarMatching.StringMeeting("12:00", "13:00")
        });

        List<CalendarMatching.StringMeeting> calendar2 = Arrays.asList(new CalendarMatching.StringMeeting[]{
                new CalendarMatching.StringMeeting("10:00", "11:30"),
                new CalendarMatching.StringMeeting("12:30", "14:30"),
                new CalendarMatching.StringMeeting("14:30", "15:00"),
                new CalendarMatching.StringMeeting("16:00", "17:00")
        });

        CalendarMatching.StringMeeting dailyBounds1 = new CalendarMatching.StringMeeting("9:00", "20:00");
        CalendarMatching.StringMeeting dailyBounds2 = new CalendarMatching.StringMeeting("10:00", "18:30");

        List<CalendarMatching.StringMeeting> result = CalendarMatching.calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2, 30);

        List<CalendarMatching.StringMeeting> expected = new ArrayList<CalendarMatching.StringMeeting>();
        expected.add(new CalendarMatching.StringMeeting("11:30", "12:00"));
        expected.add(new CalendarMatching.StringMeeting("15:00", "16:00"));
        expected.add(new CalendarMatching.StringMeeting("18:00", "18:30"));
        assertArrayEquals(expected.toArray(), result.toArray());

    }
}
