package com.swen262;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.LinkedHashSet;
// Used linkedhashset per time complexity table here: https://gist.github.com/psayre23/c30a821239f4818b0709

/**
 *
 */
public class Database {

    private LinkedHashSet<Song> songs;
    private LinkedHashSet<Release> releases;
    private LinkedHashSet<Artist> artists;

    public Database(String song_csv, String release_csv, String artist_csv){
        artists = new LinkedHashSet<>();
        releases = new LinkedHashSet<>();
        songs = new LinkedHashSet<>();

        System.out.println("Initializing the market's finest music database!");
        CSVParser(song_csv,release_csv,artist_csv);
    }

    private void CSVParser(String song_csv, String release_csv, String artist_csv){
        try {
            CSVReader artist_csv_reader = new CSVReader(new FileReader(artist_csv));
            String[] artist_csv_record;

            while ((artist_csv_record = artist_csv_reader.readNext()) != null){
                Artist curArtist = artistParser(artist_csv_record);
                artists.add(curArtist);
            }

            // songs need to come l8r
            CSVReader song_csv_reader = new CSVReader(new FileReader(song_csv));
            String[] song_csv_record;
            while ((song_csv_record = song_csv_reader.readNext()) != null){
                songParser(song_csv_record);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    private Artist artistParser(String[] artist_attributes){
        String guid = artist_attributes[0];
        String name = artist_attributes[1];
        String type = artist_attributes[2];
        return new Artist(guid,name,type);
    }

    private void songParser(String[] song_attributes){

        String song_guid = song_attributes[0];
        String artist_guid = song_attributes[1];
        int duration = Integer.parseInt(song_attributes[2]);
        String title = song_attributes[3];
        //return new Song(title,artist_guid,duration,song_guid);
    }


}
