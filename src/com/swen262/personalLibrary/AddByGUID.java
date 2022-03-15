package com.swen262.personalLibrary;

import com.swen262.database.Database;
import com.swen262.exceptions.GUIDNotFoundException;
import com.swen262.model.Release;
import com.swen262.model.Song;

import java.time.LocalDate;

public class AddByGUID implements Action {

    private LocalDate date;

    public AddByGUID() {
        date = LocalDate.now();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void performAction(Object o) throws Exception {
        PersonalLibrary library = PersonalLibrary.getActiveInstance();

        if (o instanceof String) {
            String GUID = (String) o;
            Database db = Database.getActiveInstance();
            Song song = db.searchSongByGUID(GUID);

            if (song == null) {
                Release release = db.searchReleaseByGUID(GUID);

                if (release == null) {
                    throw new GUIDNotFoundException();
                } else {
                    library.addRelease(release);
                }
            } else {
                library.addSong(song, date);
            }
        }
    }
}
