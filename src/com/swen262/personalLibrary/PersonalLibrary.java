package com.swen262.personalLibrary;

import com.swen262.database.Database;
import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;

import java.util.HashSet;
import java.util.LinkedList;

public class PersonalLibrary {
    private final LinkedList<Song> songs;
    private final LinkedList<Release> releases;

    private static PersonalLibrary activeInstance;

    public PersonalLibrary(LinkedList<Song> songs, LinkedList<Release> releases) {
        activeInstance = this;
        this.songs = songs;
        this.releases = releases;
    }

    public static PersonalLibrary getActiveInstance() {
        if (activeInstance == null) {
            // TODO: Load a personal library if it exists. otherwise create a new one

            return new PersonalLibrary();
        } else {
            return activeInstance;
        }
    }

    public PersonalLibrary() {
        this(new LinkedList<>(), new LinkedList<>());
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
            artists.add(song.getArtist());
        }
        for (Release release : releases) {
            for (Song song : release.getTracks()) {
                artists.add(song.getArtist());
            }
        }
        return artists;
    }

    protected void addSong(Song song) {
        if (!songs.contains(song)) {
            songs.add(song);
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