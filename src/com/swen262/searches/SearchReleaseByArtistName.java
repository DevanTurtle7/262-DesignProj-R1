package com.swen262.searches;

import java.util.LinkedList;

import com.swen262.Database;
import com.swen262.Release;

public class SearchReleaseByArtistName implements SongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query, Database db) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : db.getReleases()){
            if(release.getArtist().getName().toLowerCase().equals(query.toLowerCase())){
                returnReleases.add(release);
            }
        }
        return returnReleases;
    }
    
}
