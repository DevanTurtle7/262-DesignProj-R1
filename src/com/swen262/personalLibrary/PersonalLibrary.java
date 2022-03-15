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

public class PersonalLibrary {
    private static HashSet<Artist> artists;
    private final LinkedList<Song> songs;
    private final LinkedList<Release> releases;
    private final HashMap<Song, LocalDate> datesAdded;
    private static HashMap<Song, Release> songsToRelease;

    private static PersonalLibrary activeInstance;

    public PersonalLibrary(LinkedList<Song> songs, LinkedList<Release> releases, HashSet<Artist> artists) {
        activeInstance = this;
        this.songs = songs;
        PersonalLibrary.artists = artists;
        this.releases = releases;
        songsToRelease = new HashMap<>();
        this.datesAdded = new HashMap<>();
    }

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

    private static Artist artistParser(String[] artist_attributes) {
        String guid = artist_attributes[3];
        String name = artist_attributes[0];
        String rtstr = artist_attributes[2];
        int rating = 0;
        if (rtstr.length() > 0) {
            rating = Integer.parseInt(rtstr);
        }
        String type = artist_attributes[1];
        return new Artist(name, type, rating, guid);
    }

    public static Artist searchArtistByGUID(String GUID) {
        for (Artist artist : artists) {
            if (GUID.equals(artist.getGUID())) {
                return artist;
            }
        }
        return null;
    }

    private static Song songParser(String[] song_attributes) {

        String title = song_attributes[0];
        String artist_guid = song_attributes[1];
        Artist artist = searchArtistByGUID(artist_guid);
        int duration = Integer.parseInt(song_attributes[2]);
        int rating = Integer.parseInt(song_attributes[3]);
        String song_guid = song_attributes[4];
        return new Song(title, artist, duration, rating, song_guid);
    }

    private static Song searchSongByGUID(String GUID) {
        for (Song song : getActiveInstance().songs) {
            if (GUID.equals(song.getGUID())) {
                return song;
            }
        }
        return null;
    }

    private static Release releaseParser(String[] release_attributes) {
        int length = release_attributes.length;
        String issue_date = release_attributes[0];
        String title = release_attributes[1];
        String artist_guid = release_attributes[2];
        Artist artist = searchArtistByGUID(artist_guid);
        String medium = release_attributes[3];
        String release_guid = release_attributes[4];
        //System.out.println("Original issue date: "+issue_date);

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

            return release;
        } catch (ParseException e) {
            System.out.println("ohno error");
            e.printStackTrace();
        }
        return null;
    }

    public PersonalLibrary() {
        this(new LinkedList<>(), new LinkedList<>(), new HashSet<>());
    }

    public LinkedList<Song> getSongs() {
        return this.songs;
    }

    public LinkedList<Release> getReleases() {
        return this.releases;
    }


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

    public int getDurationFromArtist(Artist artist) {
        int duration = 0;

        for (Song song : songs) {
            if (song.getArtist() == artist) {
                duration += song.getDuration();
            }
        }

        return duration;
    }
}