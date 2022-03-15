package com.swen262.personalLibrary;

import com.swen262.model.Release;
import com.swen262.model.Song;

import java.util.LinkedList;

/**
 * Inherits from Action. Concrete Command.
 */
public class RemoveByGUID implements Action {

    /**
     * Searches database to find an object with the GUID> Then, removes from library,
     * @param o Generic Java Object. In This case, should be a String.
     * @throws Exception
     */
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
