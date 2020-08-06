package algoexpert.calendarmatching;

import java.util.*;
import java.util.stream.Collectors;

public class CalendarMatching {

    public static List<StringMeeting> calendarMatching(List<StringMeeting> calendar1, StringMeeting dailyBounds1,
                                                       List<StringMeeting> calendar2, StringMeeting dailyBounds2,
                                                       int meetingDuration) {
        PriorityQueue<PointOfTime> timeQueue = new PriorityQueue(new Comparator<PointOfTime>() {
            @Override
            public int compare(PointOfTime t1, PointOfTime t2) {
                int transformedTime1 = transformTime(t1.time);
                int transformedTime2 = transformTime(t2.time);
                if (transformedTime1 == transformedTime2) return 0;
                if (transformedTime1 > transformedTime2) return 1;
                return -1;
            }
        });

        for (StringMeeting meeting : calendar1) {
            timeQueue.add(new PointOfTime(meeting.start, PointOfTimeType.START));
            timeQueue.add(new PointOfTime(meeting.end, PointOfTimeType.END));
        }

        timeQueue.add(new PointOfTime(dailyBounds1.start, PointOfTimeType.DAY_START));
        timeQueue.add(new PointOfTime(dailyBounds1.end, PointOfTimeType.DAY_END));

        for (StringMeeting meeting : calendar2) {
            timeQueue.add(new PointOfTime(meeting.start, PointOfTimeType.START));
            timeQueue.add(new PointOfTime(meeting.end, PointOfTimeType.END));
        }

        timeQueue.add(new PointOfTime(dailyBounds2.start, PointOfTimeType.DAY_START));
        timeQueue.add(new PointOfTime(dailyBounds2.end, PointOfTimeType.DAY_END));

        int numberOfSimultaneousMeetings = 0;
        int startOfTheDay = 0;
        int endOfTheDay = 0;
        List<StringMeeting> freeSlots = new ArrayList<>();
        while (!timeQueue.isEmpty()) {
            PointOfTime pointOfTime = timeQueue.poll();
            if(pointOfTime.type == PointOfTimeType.START) numberOfSimultaneousMeetings++;
            if(pointOfTime.type == PointOfTimeType.END) numberOfSimultaneousMeetings--;
            if(pointOfTime.type == PointOfTimeType.DAY_START) startOfTheDay++;
            if(pointOfTime.type == PointOfTimeType.DAY_END) endOfTheDay++;
            if(numberOfSimultaneousMeetings == 0 && startOfTheDay == 2 && endOfTheDay == 0) {
                String freeSlotStart = pointOfTime.time;
                PointOfTime nextPointOfTime = timeQueue.poll();
                if(nextPointOfTime != null) {
                    String freeSlotEnd = nextPointOfTime.time;
                    StringMeeting freeSlot = new StringMeeting(freeSlotStart, freeSlotEnd);
                    freeSlots.add(freeSlot);
                    if(nextPointOfTime.type == PointOfTimeType.START) numberOfSimultaneousMeetings++;
                    if(nextPointOfTime.type == PointOfTimeType.END) numberOfSimultaneousMeetings--;
                    if(pointOfTime.type == PointOfTimeType.DAY_END) endOfTheDay++;
                }
            }
        }

        List<StringMeeting> result = freeSlots.stream().filter(freeSlot -> {
           return transformTime(freeSlot.end) - transformTime(freeSlot.start) >=  meetingDuration;
        }).collect(Collectors.toList());

        return result;
    }

    private static int transformTime(String time) {
        String splittedTime[] = time.split(":");
        if (splittedTime.length == 2) {
            return Integer.valueOf(splittedTime[0]) * 60 + Integer.valueOf(splittedTime[1]);
        } else {
            return 0;
        }
    }


    static class PointOfTime {
        public String time;
        public int type;

        public PointOfTime(String time, int type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public String toString() {
            return "PointOfTime{" +
                    "time='" + time + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    static class PointOfTimeType {
        public final static int START = 0;
        public final static int END = 1;
        public final static int DAY_START = 2;
        public final static int DAY_END = 3;
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "StringMeeting{" +
                    "start='" + start + '\'' +
                    ", end='" + end + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringMeeting meeting = (StringMeeting) o;
            return Objects.equals(start, meeting.start) &&
                    Objects.equals(end, meeting.end);
        }
    }
}
