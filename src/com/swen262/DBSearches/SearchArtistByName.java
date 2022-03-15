package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.model.Artist;

import java.util.LinkedList;

public class SearchArtistByName implements DBSearcher<Artist> {

    @Override
    public LinkedList<Artist> algorithm(String query) {
        LinkedList<Artist> returnArtists = new LinkedList<>();
        for (Artist artist : Database.getActiveInstance().getArtists()) {
            if (artist.getName().toLowerCase().contains(query.toLowerCase())) {
                returnArtists.add(artist);
            }
        }
        return returnArtists;
    }

}
