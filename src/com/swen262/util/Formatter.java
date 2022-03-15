/**
 * Provides formatters for text output
 *
 * @author Devan Kavalchek
 */

package com.swen262.util;

public class Formatter {

    private static final int MS_IN_HOUR = 3600000;
    private static final int MS_IN_MINUTE = 60000;
    private static final int MS_IN_SECOND = 1000;

    /**
     * Formats a given duration
     * @param ms The duration in milliseconds
     * @return A string in Hh Mm Ss MSms format
     */
    public static String formatDuration(int ms) {
        int remainingMS = ms;

        // Get all the fields
        int hours = Math.floorDiv(remainingMS, MS_IN_HOUR);
        remainingMS -= hours * MS_IN_HOUR;
        int minutes = Math.floorDiv(remainingMS, MS_IN_MINUTE);
        remainingMS -= minutes * MS_IN_MINUTE;
        int seconds = Math.floorDiv(remainingMS, MS_IN_SECOND);
        remainingMS -= seconds * MS_IN_SECOND;
        int milliseconds = remainingMS;

        // Assemble the string
        String duration = minutes + "m " + seconds + "s " + milliseconds + "ms";

        // Check if hours need to be added
        if (hours > 0) {
            duration = hours + "h " + duration;
        }

        // Return the string
        return duration;
    }
}
