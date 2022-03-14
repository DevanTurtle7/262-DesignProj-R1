package com.swen262.personalLibrary;

import com.swen262.Main;
import com.swen262.Release;
import com.swen262.Song;
import com.swen262.database.Database;

import java.util.LinkedList;

public class RemoveByGUID implements Action{
    @Override
    public void performAction(Object o){
        PersonalLibrary library = PersonalLibrary.getActiveInstance();

        if(o instanceof String){
            String GUID = (String) o;
            LinkedList<Song> songs = library.getSongs();
            LinkedList<Release> releases = library.getReleases();

            for (Song song : songs) {
                if (song.getGUID().equals(GUID)) {
                    library.removeSong(song);
                }
            }

            for (Release release : releases) {
                if (release.getGUID().equals(GUID)) {
                    library.removeRelease(release);
                }
            }
        }
    }
}
