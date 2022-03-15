package com.swen262.librarySearches;

import java.util.Collections;
import java.util.LinkedList;

import com.swen262.model.Release;
import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;

public class SearchReleaseByTrackGUID implements LibrarySongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : PersonalLibrary.getActiveInstance().getReleases()){
            for(Song song : release.getTracks()){
                if(song.getGUID().equals(query)){
                    returnReleases.add(release);
                }
            }
        }
        Collections.sort(returnReleases);
        return returnReleases;
    }
    
}
