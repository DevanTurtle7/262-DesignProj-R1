package com.swen262.librarySearches;

import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Searches the PersonalLibrary for Songs
 * with a Release GUID matching the query
 */
public class SearchSongByReleaseGUID implements LibrarySearcher<Song> {

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for (Song song : PersonalLibrary.getActiveInstance().getSongs()) {
            if (song.getRelease().getGUID().equals(query)) {
                returnSongs.add(song);
            }
        }
        Collections.sort(returnSongs);
        return returnSongs;
    }

}
