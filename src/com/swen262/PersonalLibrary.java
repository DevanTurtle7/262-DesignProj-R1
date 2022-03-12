package com.swen262;

import java.util.LinkedList;

public class PersonalLibrary {
    private LinkedList<Song> songs;
    private LinkedList<Release> releases;

    public PersonalLibrary(){
        this.songs = new LinkedList<>();
        this.releases = new LinkedList<>();
    }

    public PersonalLibrary(LinkedList<Song> songs, LinkedList<Release> releases){
        this.songs = songs;
        this.releases = releases;
    }

    public LinkedList<Song> getSongs(){
        return this.songs;
    }

    public LinkedList<Release> getReleases(){
        return this.releases;
    }

    public LinkedList<Artist> getArtists(){
        LinkedList<Artist> artists = new LinkedList<>();
        Artist artist;
        for (Song song : songs) {
            artist = song.getArtist();
            if(!artists.contains(artist)){
                artists.add(artist);
            }
        }
        for (Release release : releases) {
            for (Song song : release.getTracks()) {
                artist = song.getArtist();
                if(!artists.contains(artist)){
                    artists.add(artist);
                }
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
