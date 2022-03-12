package com.swen262;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class Release {
    private Date issueDate;
    private String title;
    private Artist artist;
    private int rating;
    private String GUID;
    private String medium;
    private LinkedList<Song> tracks;

    public Release(Date issueDate, String title, Artist artist, String medium, LinkedList<Song> tracks){
        this.issueDate = issueDate;
        this.title = title;
        this.artist = artist;
        this.medium = medium;
        this.tracks = tracks;
        this.rating = calculateRating();
        this.GUID = UUID.fromString(title+artist.toString()+tracks.toString()).toString();
    }

    public Release(String title, Artist artist, String medium, LinkedList<Song> tracks){
        this(new Date(), title, artist, medium, tracks);
        
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

    public LinkedList<Song> getTracks(){
        return this.tracks;
    }
}
