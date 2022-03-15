package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Release;

import java.util.LinkedList;

/**
 * Searches the DB for Releases with an Artist GUID matching the query
 */
public class SearchReleaseByArtistGUID implements DBSearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for (Release release : Database.getActiveInstance().getReleases()) {
            if (release.getArtist().getGUID().equals(query)) {
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }

}
