package com.swen262.personalLibrary;

import com.swen262.database.Database;
import com.swen262.exceptions.GUIDNotFoundException;
import com.swen262.model.Song;

public class RateByGUID implements Action {

    private int rating;

    public RateByGUID() {
        rating = 0;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public void performAction(Object o) throws Exception {
        PersonalLibrary library = PersonalLibrary.getActiveInstance();

        if (o instanceof String) {
            String GUID = (String) o;
            Database db = Database.getActiveInstance();
            Song song = db.searchSongByGUID(GUID);

            if (song == null) {
                throw new GUIDNotFoundException();
            } else {
                song.rate(rating);
            }
        }
    }
}
