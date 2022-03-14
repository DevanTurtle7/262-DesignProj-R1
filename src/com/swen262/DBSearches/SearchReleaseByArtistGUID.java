package com.swen262.DBSearches;

import java.util.LinkedList;

import com.swen262.database.Database;
import com.swen262.Release;

public class SearchReleaseByArtistGUID implements SongSearcher<Release> {

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : Database.getActiveInstance().getReleases()){
            if(release.getArtist().getGUID().equals(query)){
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }
    
}
