package com.swen262.personalLibrary;

import com.swen262.model.Release;
import com.swen262.model.Song;

import java.util.LinkedList;

public class RemoveByGUID implements Action {
    @Override
    public void performAction(Object o) {
        PersonalLibrary library = PersonalLibrary.getActiveInstance();

        if (o instanceof String) {
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
