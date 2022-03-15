package com.swen262.librarySearches;

import java.util.Collections;
import java.util.LinkedList;

import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;

public class SearchSongByReleaseGUID implements LibrarySongSearcher<Song>{

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for(Song song : PersonalLibrary.getActiveInstance().getSongs()){
            if(song.getRelease().getGUID().equals(query)){
                returnSongs.add(song);
            }
        }
        Collections.sort(returnSongs);
        return returnSongs;
    }
    
}
