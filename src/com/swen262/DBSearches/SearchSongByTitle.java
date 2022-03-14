package com.swen262.DBSearches;

import java.util.LinkedList;

import com.swen262.database.Database;
import com.swen262.Song;

public class SearchSongByTitle implements SongSearcher<Song>{

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for(Song song : Database.getActiveInstance().getSongs()){
            if(song.getTitle().toLowerCase().contains(query.toLowerCase())){
                returnSongs.add(song);
            }
        }
        return returnSongs;
    }
    
}
