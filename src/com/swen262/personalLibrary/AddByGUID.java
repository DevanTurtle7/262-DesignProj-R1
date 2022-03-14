package com.swen262.personalLibrary;

import com.swen262.Main;
import com.swen262.Release;
import com.swen262.Song;
import com.swen262.database.Database;
import com.swen262.exceptions.GUIDNotFoundException;

public class AddByGUID implements Action{
    private PersonalLibrary library;

    public AddByGUID(PersonalLibrary library){
        this.library = library;
    }

    @Override
    public void performAction(Object o) throws Exception {
        if(o instanceof String){
            String GUID = (String) o;
            Database db = Main.getDB();
            Song song = db.searchSongByGUID(GUID);

            if (song == null) {
                Release release = db.searchReleaseByGUID(GUID);

                if (release == null) {
                    throw new GUIDNotFoundException();
                } else {
                    library.addRelease(release);
                }
            } else {
                library.addSong(song);
            }
        }
    }
}
