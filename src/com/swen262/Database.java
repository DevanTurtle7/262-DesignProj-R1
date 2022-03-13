package com.swen262;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Database {

    private LinkedList<Song> songs;
    private LinkedList<Release> releases;
    private LinkedList<Artist> artists;

    public Database(String song_csv, String release_csv, String artist_csv){
        System.out.println("Initializing the market's finest music database!");
        CSVParser(song_csv,release_csv,artist_csv);
    }

    public void CSVParser(String song_csv, String release_csv, String artist_csv){
        try {
            BufferedReader song_csv_reader = new BufferedReader(new FileReader(song_csv));
        } catch (Exception e) {
            e.getMessage();
        }
    }


}
