package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Release;
import com.swen262.model.Song;

import java.util.LinkedList;

public class SearchReleaseByTrackName implements DBSearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for (Release release : Database.getActiveInstance().getReleases()) {
            for (Song song : release.getTracks()) {
                if (song.getTitle().equals(query)) {
                    returnReleases.add(release);
                }
            }
        }
        return returnReleases;
    }

}
