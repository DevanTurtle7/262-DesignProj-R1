package com.swen262.librarySearches;

import com.swen262.model.Release;
import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Searches the PersonalLibrary for Releases
 * with a Song name matching the query
 */
public class SearchReleaseByTrackName implements LibrarySearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for (Release release : PersonalLibrary.getActiveInstance().getReleases()) {
            for (Song song : release.getTracks()) {
                if (song.getTitle().equalsIgnoreCase(query)) {
                    returnReleases.add(release);
                }
            }
        }
        Collections.sort(returnReleases);
        return returnReleases;
    }

}
