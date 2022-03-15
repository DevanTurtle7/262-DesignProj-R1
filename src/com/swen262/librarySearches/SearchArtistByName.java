package com.swen262.librarySearches;

import java.util.Collections;
import java.util.LinkedList;

import com.swen262.Artist;
import com.swen262.personalLibrary.PersonalLibrary;

public class SearchArtistByName implements LibrarySongSearcher<Artist>{

    @Override
    public LinkedList<Artist> algorithm(String query) {
        LinkedList<Artist> returnArtists = new LinkedList<>();
        for(Artist artist : PersonalLibrary.getActiveInstance().getArtists()){
            if(artist.getName().toLowerCase().contains(query.toLowerCase())){
                returnArtists.add(artist);
            }
        }
        Collections.sort(returnArtists);
        return returnArtists;
    }
    
}
