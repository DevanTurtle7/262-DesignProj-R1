package com.swen262.model;

public class Song implements Comparable<Song>{
    private String title;
    private Artist artist;
    private int duration;
    private int rating;
    private String GUID;
    private Release release;

    public Song(String title, Artist artist, int duration, int rating, String GUID){
        this.GUID = GUID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.rating = rating;
    }

    public Song(String title, Artist artist, int duration, String GUID){
        this(title, artist, duration, 0, GUID);
    }

    public String getTitle(){
        return this.title;
    }

    public void setRelease(Release release){
        this.release = release;
    }

    public Release getRelease() {
        return release;
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

    public void rate(int rate){
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

    @Override
    public int compareTo(Song o) {
        return this.rating - o.rating;
    }
}
