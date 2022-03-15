package com.swen262.librarySearches;

import com.swen262.model.Release;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Searches the PersonalLibrary for Releases 
 * with an Artist name matching the query
 */
public class SearchReleasebyArtistName implements LibrarySearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for (Release release : PersonalLibrary.getActiveInstance().getReleases()) {
            if (release.getArtist().getName().equalsIgnoreCase(query)) {
                returnReleases.add(release);
            }
        }
        Collections.sort(returnReleases);
        return returnReleases;
    }

}
