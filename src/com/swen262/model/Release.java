package com.swen262.model;

import java.util.Date;
import java.util.LinkedList;

public class Release implements Comparable<Release> {
    private final Date issueDate;
    private final String title;
    private final Artist artist;
    private final int rating;
    private final String GUID;
    private final String medium;
    private int duration;
    private final LinkedList<Song> tracks;

    /**
     * Creates a new object representing a Release
     * @param issueDate a date object representing the date added to a user's library
     * @param title string representation of the title
     * @param artist the Release's artist in the form of an Artist object
     * @param medium the medium of the Release (CD, vinyl, digital, etc.)
     * @param tracks list of songs on this Release
     * @param GUID unique string to represent the Release
     */
    public Release(Date issueDate, String title, Artist artist, String medium, LinkedList<Song> tracks, String GUID) {
        this.issueDate = issueDate;
        this.title = title;
        this.artist = artist;
        this.medium = medium;
        this.tracks = tracks;
        this.rating = calculateRating();
        this.GUID = GUID;
        for (Song song : tracks) {
            duration += song.getDuration();
        }
    }

    /**
     * Creates a new object representing a Release with the issue date set to the current date by default
     * @param title string representation of the title
     * @param artist the Release's artist in the form of an Artist object
     * @param medium the medium of the Release (CD, vinyl, digital, etc.)
     * @param tracks list of songs on this Release
     * @param GUID unique string to represent the Release
     */
    public Release(String title, Artist artist, String medium, LinkedList<Song> tracks, String GUID) {
        this(new Date(), title, artist, medium, tracks, GUID);

    }

    /**
     * calculates the average rating of all the tracks stored in this instace
     * @return an integer of the average song rating
     */
    private int calculateRating() {
        int totalRate = 0;
        for (Song song : tracks) {
            totalRate += song.getRating();
        }
        return Math.round(totalRate / tracks.size());
    }

    /**
     * returns the issueDate stored in this instance
     * @return a date representation of issueDate
     */
    public Date getIssueDate() {
        return this.issueDate;
    }

    /**
     * returns the title stored in this instance
     * @return a string representation of title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * returns the artist stored in this instance
     * @return an Artist object
     */
    public Artist getArtist() {
        return this.artist;
    }

    /**
     * returns the rating stored in this instance
     * @return an integer representation of rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * returns the GUID stored in this instance
     * @return a string representation of GUID
     */
    public String getGUID() {
        return this.GUID;
    }

    /**
     * returns the medium stored in this instance
     * @return a string representation of medium
     */
    public String getMedium() {
        return this.medium;
    }

    /**
     * returns the tracks stored in this instance
     * @return a LinkedList<Song> representation of tracks
     */
    public LinkedList<Song> getTracks() {
        return this.tracks;
    }

    /**
     * compares a Release object to this instance of Release
     * @return an integer of 0 if the objects are equal, and an integer not equal to 0 if they are not equal
     */
    @Override
    public int compareTo(Release o) {
        return this.rating - o.rating;
    }

    /**
     * calculates the duration of all of the tracks stored in this instance
     * @return an integer representation of duration
     */
    public int getDuration() {
        int duration = 0;

        for (Song songs : tracks) {
            duration += songs.getDuration();
        }

        return duration;
    }
}
