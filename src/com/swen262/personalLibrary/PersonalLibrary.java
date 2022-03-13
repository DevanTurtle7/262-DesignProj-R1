package com.swen262.personalLibrary;

import com.swen262.Artist;
import com.swen262.Release;
import com.swen262.Song;

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

    public void removeSong(Song song){
        if(songs.contains(song)){
            songs.remove(song);
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
