package com.swen262;

public class Artist {
    private String GUID;
    private String name;
    private String type;
    private int rating;

    public Artist(String name, String type, int rating, String GUID){
        this.GUID = GUID;
        this.name = name;
        this.type = type;
        this.rating = rating;
    }

    public Artist(String GUID, String name, String type){
        this(name, type, 0, GUID);
    }

    public String getGUID(){
        return this.GUID;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public int getRating(){
        return this.rating;
    }

    @Override
    public String toString(){
        return (name + ", type: " + type + ", rating: " + rating);
    }
}
