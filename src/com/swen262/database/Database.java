package com.swen262.database;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
// Used linkedhashset per time complexity table here: https://gist.github.com/psayre23/c30a821239f4818b0709

/**
 *
 */
public class Database {

    private final LinkedHashSet<Song> songs;
    private final LinkedHashSet<Release> releases;
    private final LinkedHashSet<Artist> artists;
    private final HashMap<Song, Release> songsToRelease;

    private final String SONG_CSV_PATH = getClass().getResource("./data/songs.csv").getPath();
    private final String ARTISTS_CSV_PATH = getClass().getResource("./data/artists.csv").getPath();
    private final String RELEASES_CSV_PATH = getClass().getResource("./data/releases.csv").getPath();

    private static Database activeInstance;

    /**
     * Constructor for database
     */
    public Database() {
        activeInstance = this;

        artists = new LinkedHashSet<>();
        releases = new LinkedHashSet<>();
        songs = new LinkedHashSet<>();

        songsToRelease = new HashMap<>();
        System.out.println("Initializing the market's finest music database!");
        CSVParser();
    }

    /**
     * Gets current database system is using
     * @return active database
     */
    public static Database getActiveInstance() {
        if (activeInstance == null) {
            return new Database();
        } else {
            return activeInstance;
        }
    }

    public LinkedHashSet<Artist> getArtists() {
        return artists;
    }

    public LinkedHashSet<Release> getReleases() {
        return releases;
    }

    public LinkedHashSet<Song> getSongs() {
        return songs;
    }

    /**
     * Parses CSVs given on website using RFC-compliant parser
     */
    private void CSVParser() {
        try {
            RFC4180Parser rfcParse = new RFC4180ParserBuilder().withQuoteChar('\'').build();
            RFC4180Parser rfcDoubleParse = new RFC4180ParserBuilder().withQuoteChar('\"').build();
            CSVReader artist_csv_reader = new CSVReaderBuilder(new FileReader(ARTISTS_CSV_PATH)).withCSVParser(rfcParse).build();
            String[] artist_csv_record;

            while ((artist_csv_record = artist_csv_reader.readNext()) != null) {
                Artist curArtist = artistParser(artist_csv_record);
                artists.add(curArtist);
            }
            // songs need to come l8r
            CSVReader song_csv_reader = new CSVReaderBuilder(new FileReader(SONG_CSV_PATH)).withCSVParser(rfcDoubleParse).build();
            String[] song_csv_record;
            while ((song_csv_record = song_csv_reader.readNext()) != null) {
                Song curSong = songParser(song_csv_record);
                songs.add(curSong);
                songsToRelease.put(curSong, null);
            }

            CSVReader release_csv_reader = new CSVReaderBuilder(new FileReader(RELEASES_CSV_PATH)).withCSVParser(rfcDoubleParse).build();
            String[] release_csv_record;
            while ((release_csv_record = release_csv_reader.readNext()) != null) {
                Release curRelease = releaseParser(release_csv_record);
                releases.add(curRelease);
            }
            System.out.println("Successfully initialized database with " + songs.size() + " songs, " + releases.size() + " releases and " + artists.size() + " artists.");
        } catch (Exception e) {
            System.out.println("something went wrong");
            e.printStackTrace();
            e.getMessage();
        }
    }

    /**
     * Parses artist from csv and creates object
     * @param artist_attributes Array of strings from CSV
     * @return Artist object to add to internal db
     */
    private Artist artistParser(String[] artist_attributes) {
        String guid = artist_attributes[0];
        String name = artist_attributes[1];
        String type = artist_attributes[2];
        return new Artist(guid, name, type);
    }

    /**
     * Returns artist object if GUID matches
     * @param GUID unique identifier for artist
     * @return artist object that has the GUID
     */
    public Artist searchArtistByGUID(String GUID) {
        for (Artist artist : artists) {
            if (GUID.equals(artist.getGUID())) {
                return artist;
            }
        }
        return null;
    }

    /**
     * Parses release from CSV and creates a valid object
     * @param release_attributes String Array from CSV
     * @return valid Release object
     */
    private Release releaseParser(String[] release_attributes) {
        int length = release_attributes.length;
        String release_guid = release_attributes[0];
        String artist_guid = release_attributes[1];
        Artist artist = searchArtistByGUID(artist_guid);
        String title = release_attributes[2];
        String issue_date = release_attributes[4];
        //System.out.println("Original issue date: "+issue_date);
        String medium = release_attributes[3];
        if (issue_date.matches("^\\d\\d\\d\\d$")) {
            issue_date += "-01-01";
        }
        if (issue_date.matches("^\\d\\d\\d\\d-\\d\\d$")) {
            issue_date += "-01";
        }
        //System.out.println(issue_date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date iss_date;
        LinkedList<Song> tracks = new LinkedList<>();
        for (int i = 5; i < length; i++) {
            String trackGUID = release_attributes[i];
            //System.out.println(track);
            Song track = searchSongByGUID(trackGUID);
            tracks.add(track);
        }
        try {
            iss_date = formatter.parse(issue_date);
            Release release = new Release(iss_date, title, artist, medium, tracks, release_guid);

            for (Song song : tracks) {
                songsToRelease.replace(song, release);
            }
            for (Song song : release.getTracks()) {
                song.setRelease(release);
            }

            return release;
        } catch (ParseException e) {
            System.out.println("ohno error");
            e.getMessage();
        }
        return null;
    }

    /**
     * Searches releases for release with matching GUID
     * @param GUID Unique identifier for release
     * @return Release object with matching GUID
     */
    public Release searchReleaseByGUID(String GUID) {
        for (Release release : releases) {
            if (GUID.equals(release.getGUID())) {
                return release;
            }
        }
        return null;
    }

    /**
     * Parses song from CSV and makes valid Song object
     * @param song_attributes String array from CSVParser
     * @return valid Song object
     */
    private Song songParser(String[] song_attributes) {

        String song_guid = song_attributes[0];
        String artist_guid = song_attributes[1];
        Artist artist = searchArtistByGUID(artist_guid);
        int duration = Integer.parseInt(song_attributes[2]);
        String title = song_attributes[3];
        return new Song(title, artist, duration, song_guid);
    }

    /**
     * Searches song for release with matching GUID
     * @param GUID Unique identifier for song
     * @return Song object with matching GUID
     */
    public Song searchSongByGUID(String GUID) {
        for (Song song : songs) {
            if (GUID.equals(song.getGUID())) {
                return song;
            }
        }
        return null;
    }

    public Release getSongRelease(Song song) {
        return songsToRelease.get(song);
    }

    public boolean isASingle(Song song) {
        return songsToRelease.get(song) == null;
    }
}
