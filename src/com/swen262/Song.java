package com.swen262;

import java.util.UUID;

public class Song {
    private String title;
    private Artist artist;
    private int duration;
    private int rating;
    private String GUID;

    public Song(String title, Artist artist, int duration, int rating){
        this.GUID = UUID.fromString((title+artist.toString())).toString();
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.rating = rating;
    }

    public Song(String title, Artist artist, int duration){
        this(title, artist, duration, 0);
    }

    public String getTitle(){
        return this.title;
    }

    public Artist getArtist(){
        return this.artist;
    }

    public int getDuration(){
        return this.duration;
    }

    public int getRating(){
        return this.rating;
    }

    public String getGUID(){
        return this.GUID;
    }

    public void Rate(int rate){
        if(rate <= 5 && rate >= 1){
            this.rating = rate;
        }else{
            System.out.println("Rating must be between 1 to 5 stars.");
        }
    }

    @Override
    public String toString(){
        return (title + ", by: " + artist.getName());
    }
}
