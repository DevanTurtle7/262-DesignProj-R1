package com.swen262.personalLibrary;

import com.swen262.Main;
import com.swen262.Song;
import com.swen262.database.Database;

import java.util.LinkedList;

public class RemoveSongByGUID implements Action{
    private PersonalLibrary library;

    public RemoveSongByGUID(PersonalLibrary library){
        this.library = library;
    }

    @Override
    public void performAction(Object o){
        if(o instanceof String){
            String GUID = (String) o;
            LinkedList<Song> songs = library.getSongs();

            for (Song song : songs) {
                if (song.getGUID().equals(GUID)) {
                    library.removeSong(song);
                }
            }
        }
    }
}
