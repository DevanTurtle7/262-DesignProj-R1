package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Song;

import java.util.LinkedList;

/**
 * Searches the DB for Songs that have a title substring matching the query
 */
public class SearchSongByTitle implements DBSearcher<Song> {

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
