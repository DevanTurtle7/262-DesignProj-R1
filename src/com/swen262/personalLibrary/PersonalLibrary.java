package com.swen262.personalLibrary;

import com.swen262.Artist;
import com.swen262.Main;
import com.swen262.Release;
import com.swen262.Song;
import com.swen262.database.Database;

import java.util.HashSet;
import java.util.LinkedList;

public class PersonalLibrary {
    private LinkedList<Song> songs;
    private LinkedList<Release> releases;

    public PersonalLibrary(LinkedList<Song> songs, LinkedList<Release> releases){
        this.songs = songs;
        this.releases = releases;
    }

    public PersonalLibrary(){
        this(new LinkedList<>(), new LinkedList<>());
    }

    public static PersonalLibrary loadPersonalLibrary() {
        // TODO: Load a personal library if it exists. otherwise create a new one

        return new PersonalLibrary();
    }

    public LinkedList<Song> getSongs(){
        return this.songs;
    }

    public LinkedList<Release> getReleases(){
        return this.releases;
    }

    public HashSet<Artist> getArtists(){
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

    public void addSong(Song song){
        if(!songs.contains(song)){
            songs.add(song);
        }
    }

    public void addSongByGUID(String GUID) {
        Database database = Main.getDB();
        Song song = database.searchSongByGUID(GUID);
        addSong(song);
    }

    public void removeSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
        }
    }

    public void removeSongByGUID(String GUID) {
        for (Song song : songs) {
            if (song.getGUID().equals(GUID)) {
                songs.remove(song);
            }
        }
    }

    public void addRelease(Release release){
        if(!releases.contains(release)){
            releases.add(release);
        }
    }

    public void removeRelease(Release release){
        if(releases.contains(release)){
            releases.remove(release);
        }
    }
}