package com.swen262.util;

public class Formatter {

    private static final int MS_IN_HOUR = 3600000;
    private static final int MS_IN_MINUTE = 60000;
    private static final int MS_IN_SECOND = 1000;

    public static String formatDuration(int ms) {
        int remainingMS = ms;

        int hours = Math.floorDiv(remainingMS, MS_IN_HOUR);
        remainingMS -= hours * MS_IN_HOUR;
        int minutes = Math.floorDiv(remainingMS, MS_IN_MINUTE);
        remainingMS -= minutes * MS_IN_MINUTE;
        int seconds = Math.floorDiv(remainingMS, MS_IN_SECOND);
        remainingMS -= seconds * MS_IN_SECOND;
        int milliseconds = remainingMS;

        String duration = minutes + "m " + seconds + "s " + milliseconds + "ms";

        if (hours > 0) {
            duration = hours + "h " + duration;
        }

        return duration;
    }
}
