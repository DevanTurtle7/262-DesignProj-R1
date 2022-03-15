package com.swen262.librarySearches;

import com.swen262.model.Artist;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Searches the PersonalLibrary for Artists with a type matching the query
 */
public class SearchArtistByType implements LibrarySearcher<Artist> {

    @Override
    public LinkedList<Artist> algorithm(String query) {
        LinkedList<Artist> returnArtists = new LinkedList<>();
        for (Artist artist : PersonalLibrary.getActiveInstance().getArtists()) {
            if (artist.getType().equalsIgnoreCase(query)) {
                returnArtists.add(artist);
            }
        }
        Collections.sort(returnArtists);
        return returnArtists;
    }

}
