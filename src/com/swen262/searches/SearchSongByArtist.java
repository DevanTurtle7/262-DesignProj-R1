package com.swen262.searches;

import java.util.LinkedList;

import com.swen262.Database;
import com.swen262.Song;

public class SearchSongByArtist implements SongSearcher<Song>{

    @Override
    public LinkedList<Song> algorithm(String query, Database db) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for(Song song : db.getSongs()){
            if(song.getArtist().getName().toLowerCase().equals(query.toLowerCase())){
                returnSongs.add(song);
            }
        }
        return returnSongs;
    }
    
}
