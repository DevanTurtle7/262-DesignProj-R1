package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Song;

import java.util.LinkedList;

/**
 * Searches the DB for Songs that have an Artist name matching the query
 */
public class SearchSongByArtist implements DBSearcher<Song> {

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for (Song song : Database.getActiveInstance().getSongs()) {
            if (song.getArtist().getName().equalsIgnoreCase(query)) {
                returnSongs.add(song);
            }
        }
        return returnSongs;
    }

}
