package com.swen262.librarySearches;

import java.util.Collections;
import java.util.LinkedList;

import com.swen262.Song;
import com.swen262.personalLibrary.PersonalLibrary;

public class SearchSongByTitle implements LibrarySongSearcher<Song>{

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for(Song song : PersonalLibrary.getActiveInstance().getSongs()){
            if(song.getTitle().toLowerCase().contains(query.toLowerCase())){
                returnSongs.add(song);
            }
        }
        Collections.sort(returnSongs);
        return returnSongs;
    }
    
}
