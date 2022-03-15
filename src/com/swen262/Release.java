package com.swen262;

import java.util.Date;
import java.util.LinkedHashSet;

public class Release implements Comparable<Release>{
    private Date issueDate;
    private String title;
    private Artist artist;
    private int rating;
    private String GUID;
    private String medium;
    private LinkedHashSet<Song> tracks;
    private int duration;

    public Release(Date issueDate, String title, Artist artist, String medium, LinkedHashSet<Song> tracks, String GUID){
        this.issueDate = issueDate;
        this.title = title;
        this.artist = artist;
        this.medium = medium;
        this.tracks = tracks;
        this.rating = calculateRating();
        this.GUID = GUID;
        for(Song song : tracks){
            duration+=song.getDuration();
        }
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

    public int getDuration() {
        return duration;
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

    @Override
    public int compareTo(Release o) {
        return this.rating - o.rating;
    }
}
