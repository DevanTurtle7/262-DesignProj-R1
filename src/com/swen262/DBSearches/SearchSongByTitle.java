package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Song;

import java.util.LinkedList;

public class SearchSongByTitle implements DBSongSearcher<Song> {

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for (Song song : Database.getActiveInstance().getSongs()) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())) {
                returnSongs.add(song);
            }
        }
        return returnSongs;
    }

}
