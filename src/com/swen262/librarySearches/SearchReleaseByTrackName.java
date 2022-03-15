package com.swen262.librarySearches;

import java.util.Collections;
import java.util.LinkedList;

import com.swen262.Release;
import com.swen262.Song;
import com.swen262.personalLibrary.PersonalLibrary;

public class SearchReleaseByTrackName implements LibrarySongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        for(Release release : PersonalLibrary.getActiveInstance().getReleases()){
            for(Song song : release.getTracks()){
                if(song.getTitle().toLowerCase().equals(query.toLowerCase())){
                    returnReleases.add(release);
                }
            }
        }
        Collections.sort(returnReleases);
        return returnReleases;
    }
    
}
