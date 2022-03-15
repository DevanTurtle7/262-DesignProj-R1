package com.swen262.DBSearches;

import java.util.LinkedList;

import com.swen262.database.Database;
import com.swen262.Release;

public class SearchReleaseByArtistName implements DBSongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : Database.getActiveInstance().getReleases()){
            if(release.getArtist().getName().toLowerCase().equals(query.toLowerCase())){
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }
    
}
