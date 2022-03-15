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

    public Release(String title, Artist artist, String medium, LinkedList<Song> tracks, String GUID) {
        this(new Date(), title, artist, medium, tracks, GUID);

    }

    private int calculateRating() {
        int totalRate = 0;
        for (Song song : tracks) {
            totalRate += song.getRating();
        }
        return Math.round(totalRate / tracks.size());
    }

    public Date getIssueDate() {
        return this.issueDate;
    }

    public String getTitle() {
        return this.title;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public int getRating() {
        return this.rating;
    }

    public String getGUID() {
        return this.GUID;
    }

    public String getMedium() {
        return this.medium;
    }

    public LinkedList<Song> getTracks() {
        return this.tracks;
    }

    @Override
    public int compareTo(Release o) {
        return this.rating - o.rating;
    }

    public int getDuration() {
        int duration = 0;

        for (Song songs : tracks) {
            duration += songs.getDuration();
        }

        return duration;
    }
}
