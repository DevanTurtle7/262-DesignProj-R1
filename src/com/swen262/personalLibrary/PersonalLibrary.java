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

    protected LinkedList<Song> getSongs(){
        return this.songs;
    }

    protected LinkedList<Release> getReleases(){
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

    protected void addSong(Song song){
        if(!songs.contains(song)){
            songs.add(song);
        }
    }

    protected void removeSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
        }
    }

    protected void addRelease(Release release){
        if(!releases.contains(release)){
            releases.add(release);
        }
    }

    protected void removeRelease(Release release){
        if(releases.contains(release)){
            releases.remove(release);
        }
    }

    public int getSongCount() {
        return songs.size();
    }
}