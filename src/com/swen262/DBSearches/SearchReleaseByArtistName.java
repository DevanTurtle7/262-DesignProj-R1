package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Release;

import java.util.LinkedList;

public class SearchReleaseByArtistName implements DBSongSearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for (Release release : Database.getActiveInstance().getReleases()) {
            if (release.getArtist().getName().equalsIgnoreCase(query)) {
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }

}
