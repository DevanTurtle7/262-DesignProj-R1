package com.swen262.DBSearches;

import com.swen262.database.Database;
import com.swen262.exceptions.InvalidInput;
import com.swen262.model.Song;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class SearchSongByDuration implements DBSongSearcher<Song> {


    @Override
    public LinkedList<Song> algorithm(String query) {
        String[] tokens = query.split(" ");
        LinkedList<Song> returnSongs = new LinkedList<>();
        try {
            String shorterOrLonger = tokens[0].toLowerCase();
            int duration = Integer.parseInt(tokens[1]);
            LinkedHashSet<Song> songs = Database.getActiveInstance().getSongs();
            if (shorterOrLonger.equals("s")) {
                for (Song song : songs) {
                    if (song.getDuration() < duration) {
                        returnSongs.add(song);
                    }
                }
            } else if (shorterOrLonger.equals("l")) {
                for (Song song : songs) {
                    if (song.getDuration() > duration) {
                        returnSongs.add(song);
                    }
                }
            } else {
                throw new InvalidInput("Query must begin with an L or S");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ensure your input is entered correctly, seperated by a space. Example: \"s 150000\" to search for songs shorter than 150000ms");
        } catch (InvalidInput IE) {
            System.out.println(IE.getMessage());
        }
        return returnSongs;
    }

}
