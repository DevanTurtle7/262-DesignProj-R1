package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Release;

import java.util.LinkedList;
import java.util.Locale;

public class SearchReleaseByTitle implements DBSearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for (Release release : Database.getActiveInstance().getReleases()) {
            if (release.getTitle().toLowerCase().contains(query.toLowerCase())) {
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }

}
