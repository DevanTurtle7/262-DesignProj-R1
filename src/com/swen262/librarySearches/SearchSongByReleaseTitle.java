package com.swen262.librarySearches;

import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Searches the PersonalLibrary for Songs
 * with a Release title matching the query
 */
public class SearchSongByReleaseTitle implements LibrarySearcher<Song> {

    @Override
    public LinkedList<Song> algorithm(String query) {
        LinkedList<Song> returnSongs = new LinkedList<>();
        for (Song song : PersonalLibrary.getActiveInstance().getSongs()) {
            if (song.getRelease().getTitle().equalsIgnoreCase(query)) {
                returnSongs.add(song);
            }
        }
        Collections.sort(returnSongs);
        return returnSongs;
    }

}
