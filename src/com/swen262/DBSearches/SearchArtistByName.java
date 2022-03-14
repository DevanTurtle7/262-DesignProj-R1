package com.swen262.DBSearches;

import java.util.LinkedList;

import com.swen262.Artist;
import com.swen262.database.Database;

public class SearchArtistByName implements SongSearcher<Artist>{

    @Override
    public LinkedList<Artist> algorithm(String query, Database db) {
        LinkedList<Artist> returnArtists = new LinkedList<>();
        for(Artist artist : db.getArtists()){
            if(artist.getName().toLowerCase().contains(query.toLowerCase())){
                returnArtists.add(artist);
            }
        }
        return returnArtists;
    }
    
}
