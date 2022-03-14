package com.swen262.searches;

import java.util.LinkedList;

import com.swen262.database.Database;
import com.swen262.Release;

public class SearchReleaseByTitle implements SongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query, Database db) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : db.getReleases()){
            if(release.getTitle().contains(query)){
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }
    
}
