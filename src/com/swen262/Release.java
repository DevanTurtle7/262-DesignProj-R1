package com.swen262;

import java.util.Date;
import java.util.LinkedHashSet;

public class Release {
    private Date issueDate;
    private String title;
    private Artist artist;
    private int rating;
    private String GUID;
    private String medium;
    private LinkedHashSet<Song> tracks;

    public Release(Date issueDate, String title, Artist artist, String medium, LinkedHashSet<Song> tracks, String GUID){
        this.issueDate = issueDate;
        this.title = title;
        this.artist = artist;
        this.medium = medium;
        this.tracks = tracks;
        this.rating = calculateRating();
        this.GUID = GUID;
    }

    public Release(String title, Artist artist, String medium, LinkedHashSet<Song> tracks, String GUID){
        this(new Date(), title, artist, medium, tracks, GUID);
        
    }

    private int calculateRating(){
        int totalRate = 0;
        for(Song song : tracks){
            totalRate += song.getRating();
        }
        return Math.round(totalRate / tracks.size());
    }

    public Date getIssueDate(){
        return this.issueDate;
    }

    public String getTitle(){
        return this.title;
    }

    public Artist getArtist(){
        return this.artist;
    }

    public int getRating(){
        return this.rating;
    }

    public String getGUID(){
        return this.GUID;
    }

    public String getMedium(){
        return this.medium;
    }

    public LinkedHashSet<Song> getTracks(){
        return this.tracks;
    }

    public int getDuration() {
        int duration = 0;

        for (Song songs : tracks) {
            duration += songs.getDuration();
        }

        return duration;
    }
}
