package com.swen262.searches;

import java.util.LinkedList;

import com.swen262.Database;
import com.swen262.Release;

public class SearchReleaseByTitle implements SongSearcher{

    @Override
    public LinkedList<String> algorithm(String query, Database db) {
        LinkedList<String> returnReleases = new LinkedList<>();
        for(Release release : db.getReleases()){
            if(release.getTitle().contains(query)){
                returnReleases.add(release.getGUID());
            }
        }
        return returnReleases;
    }
    
}
