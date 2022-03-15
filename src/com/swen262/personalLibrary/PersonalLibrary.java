package com.swen262.personalLibrary;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.swen262.database.Database;
import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Internal representation of the user's music library
 * Reciever in Command Pattern
 */
public class PersonalLibrary {
    private static HashSet<Artist> artists;
    private final LinkedList<Song> songs;
    private final LinkedList<Release> releases;
    private final HashMap<Song, LocalDate> datesAdded;
    private static HashMap<Song, Release> songsToRelease;

    private static PersonalLibrary activeInstance;

    /**
     * Constructor for library
     * @param songs List of songs
     * @param releases List of releases
     * @param artists List of artists
     */
    public PersonalLibrary(LinkedList<Song> songs, LinkedList<Release> releases, HashSet<Artist> artists) {
        activeInstance = this;
        this.songs = songs;
        PersonalLibrary.artists = artists;
        this.releases = releases;
        songsToRelease = new HashMap<>();
        this.datesAdded = new HashMap<>();
    }

    /**
     * Creates and persists the active instance of the library
     * @return Constructed PersonalLibrary object
     */
    public static PersonalLibrary getActiveInstance() {
        if (activeInstance == null) {
            try {
                // TODO: Load a personal library if it exists. otherwise create a new one
                File artistCSV = new File("src/com/swen262/personalLibrary/data/artists.csv");
                File songCSV = new File("src/com/swen262/personalLibrary/data/songs.csv");
                File releaseCSV = new File("src/com/swen262/personalLibrary/data/releases.csv");
                LinkedList<Song> songs = new LinkedList<>();
                LinkedList<Release> releases = new LinkedList<>();
                songsToRelease = new HashMap<>();
                artists = new HashSet<>();
                if (songCSV.createNewFile() && releaseCSV.createNewFile() && artistCSV.createNewFile()) {
                    // create new release and songVs
                    return new PersonalLibrary(songs, releases, artists);
                } else {
                    RFC4180Parser rfcDoubleParse = new RFC4180ParserBuilder().withQuoteChar('\"').build();

                    //artists

                    CSVReader artist_csv_reader = new CSVReaderBuilder(new FileReader(artistCSV)).withCSVParser(rfcDoubleParse).build();
                    String[] artist_csv_record;

                    while ((artist_csv_record = artist_csv_reader.readNext()) != null) {
                        Artist curArtist = artistParser(artist_csv_record);
                        artists.add(curArtist);
                        //System.out.println(curArtist.getName());
                    }

                    //songs
                    CSVReader song_csv_reader = new CSVReaderBuilder(new FileReader(songCSV)).withCSVParser(rfcDoubleParse).build();
                    String[] song_csv_record;
                    while ((song_csv_record = song_csv_reader.readNext()) != null) {
                        Song curSong = songParser(song_csv_record);
                        songs.add(curSong);
                        songsToRelease.put(curSong, null);
                        //System.out.println(curSong.getTitle());
                    }

                    CSVReader release_csv_reader = new CSVReaderBuilder(new FileReader(releaseCSV)).withCSVParser(rfcDoubleParse).build();
                    String[] release_csv_record;
                    while ((release_csv_record = release_csv_reader.readNext()) != null) {
                        Release curRelease = releaseParser(release_csv_record);
                        releases.add(curRelease);
                    }
                    return new PersonalLibrary(songs, releases, artists);
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        } else {
            return activeInstance;
        }
        return null;
    }

    /**
     * Gets GUID from csv and returns valid artist from database
     * @param artist_attributes String array from csv
     * @return Valid Artist Object
     */
    private static Artist artistParser(String[] artist_attributes) {
        String guid = artist_attributes[3];
        Database database = Database.getActiveInstance();

        for (Artist artist : database.getArtists()) {
            if (artist.getGUID().equals(guid)) {
                return artist;
            }
        }

        return null;
    }

    /**
     * Returns artist object if GUID matches
     * @param GUID unique identifier for artist
     * @return artist object that has the GUID
     */
    public static Artist searchArtistByGUID(String GUID) {
        for (Artist artist : artists) {
            if (GUID.equals(artist.getGUID())) {
                return artist;
            }
        }
        return null;
    }

    /**
     * Gets GUID from csv and returns valid song from database
     * @param song_attributes String array from csv
     * @return Valid Song Object
     */
    private static Song songParser(String[] song_attributes) {
        String song_guid = song_attributes[4];
        Database database = Database.getActiveInstance();

        for (Song song : database.getSongs()) {
            if (song.getGUID().equals(song_guid)) {
                return song;
            }
        }

        return null;
    }

    /**
     * Returns song object if GUID matches
     * @param GUID unique identifier for song
     * @return song object that has the GUID
     */
    private static Song searchSongByGUID(String GUID) {
        for (Song song : getActiveInstance().songs) {
            if (GUID.equals(song.getGUID())) {
                return song;
            }
        }
        return null;
    }

    /**
     * Gets GUID from csv and returns valid release from database
     * @param release_attributes String array from csv
     * @return Valid Release Object
     */
    private static Release releaseParser(String[] release_attributes) {
        String release_guid = release_attributes[4];
        Database database = Database.getActiveInstance();

        for (Release release : database.getReleases()) {
            if (release.getGUID().equals(release_guid)) {
                return release;
            }
        }

        return null;
    }

    // alternate constructor if no file is found
    public PersonalLibrary() {
        this(new LinkedList<>(), new LinkedList<>(), new HashSet<>());
    }

    public LinkedList<Song> getSongs() {
        return this.songs;
    }

    public LinkedList<Release> getReleases() {
        return this.releases;
    }

    /**
     * Searches songs and releases for artists
     * @return Valid Artist Object
     */
    public HashSet<Artist> getArtists() {
        HashSet<Artist> artists = new HashSet<>();
        for (Song song : songs) {
            //System.out.println(song.getArtist().getName());
            artists.add(song.getArtist());
        }
        for (Release release : releases) {
            for (Song song : release.getTracks()) {
                artists.add(song.getArtist());
            }
        }
        return artists;
    }

    // if song not in list, add song. Then, add date.
    protected void addSong(Song song, LocalDate date) {
        if (!songs.contains(song)) {
            songs.add(song);
            datesAdded.put(song, date);
        }
    }

    protected void removeSong(Song song) {
        songs.remove(song);
    }

    protected void addRelease(Release release) {
        if (!releases.contains(release)) {
            releases.add(release);
        }
    }

    protected void removeRelease(Release release) {
        releases.remove(release);
    }

    public int getSongCount() {
        return songs.size();
    }

    public int getReleaseCount() {
        return releases.size();
    }

    /**
     * Gets all songs that artist has made
     * @param artist Valid Artist Object
     * @return Set of songs artist has made
     */
    public HashSet<Song> getSongsFromArtist(Artist artist) {
        HashSet<Song> songsFromArtist = new HashSet<>();
        Database database = Database.getActiveInstance();

        for (Song song : songs) {
            if (song.getArtist() == artist) {
                if (database.isASingle(song)) {
                    songsFromArtist.add(song);
                }
            }
        }

        return songsFromArtist;
    }

    /**
     * Gets all releases that artist has made
     * @param artist Valid Artist Object
     * @return Set of releases artist has made
     */
    public HashSet<Release> getReleasesFromArtist(Artist artist) {
        HashSet<Release> releasesFromArtist = new HashSet<>();
        Database database = Database.getActiveInstance();

        for (Song song : songs) {
            if (song.getArtist() == artist) {
                if (!database.isASingle(song)) {
                    Release release = database.getSongRelease(song);
                    releasesFromArtist.add(release);
                }
            }
        }

        for (Release release : releases) {
            releasesFromArtist.add(release);
        }

        return releasesFromArtist;
    }

    /**
     * Gets total duration of all songs artist has made
     * @param artist Valid Artist object
     * @return Cumulative duration from songs and releases
     */
    public int getDurationFromArtist(Artist artist) {
        int duration = 0;

        for (Song song : songs) {
            if (song.getArtist() == artist) {
                duration += song.getDuration();
            }
        }

        for (Release release : releases) {
            duration += release.getDuration();
        }

        return duration;
    }
}