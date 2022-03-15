package com.swen262.DBSearches;

import java.util.LinkedList;

import com.swen262.database.Database;
import com.swen262.model.Release;

public class SearchReleaseByTitle implements DBSongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : Database.getActiveInstance().getReleases()){
            if(release.getTitle().contains(query)){
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }
    
}
