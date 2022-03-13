package com.swen262;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public LinkedHashSet<Artist> getArtists(){
        return artists;
    }

    public LinkedHashSet<Release> getReleases(){
        return releases;
    }

    public LinkedHashSet<Song> getSongs(){
        return songs;
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
                Song curSong = songParser(song_csv_record);
                songs.add(curSong);
            }

            CSVReader release_csv_reader = new CSVReader(new FileReader(release_csv));
            String[] release_csv_record;
            while ((release_csv_record = release_csv_reader.readNext()) != null){
                Release curRelease = releaseParser(release_csv_record);
                releases.add(curRelease);
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

    private Artist searchArtistByGUID(String GUID){
        for (Artist artist:artists) {
            if (GUID.equals(artist.getGUID())){
                return artist;
            }
        }
        return null;
    }

    private Release releaseParser(String[] release_attributes){
        int length = release_attributes.length;
        String release_guid = release_attributes[0];
        String artist_guid = release_attributes[1];
        Artist artist = searchArtistByGUID(artist_guid);
        String title = release_attributes[2];
        String issue_date = release_attributes[3];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date iss_date;
        String medium = release_attributes[4];
        LinkedHashSet<Song> tracks = new LinkedHashSet<>();
        String track;
        for (int i = 5; i < length ; i++) {
            track = release_attributes[i];
            tracks.add(searchSongByGUID(track));
        }
        try{
            iss_date = formatter.parse(issue_date);
            return new Release(iss_date,title,artist,medium,tracks,release_guid);
        } catch (ParseException e){
            e.getMessage();
        }
        return null;

    }

    private Song songParser(String[] song_attributes){

        String song_guid = song_attributes[0];
        String artist_guid = song_attributes[1];
        Artist artist = searchArtistByGUID(artist_guid);
        int duration = Integer.parseInt(song_attributes[2]);
        String title = song_attributes[3];
        return new Song(title,artist,duration,song_guid);
    }

    private Song searchSongByGUID(String GUID){
        for (Song song: songs) {
            if (GUID.equals(song.getGUID())){
                return song;
            }
        }
        return null;
    }


}
