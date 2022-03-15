package com.swen262.personalLibrary;

import com.swen262.database.Database;
import com.swen262.exceptions.GUIDNotFoundException;
import com.swen262.model.Song;

/**
 * Inherits from Action. Concrete Command.
 */
public class RateByGUID implements Action {

    private int rating;

    public RateByGUID() {
        rating = 0;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Searches database to find an object with the GUID> Then, adds a rating to the object,
     * @param o Generic Java Object. In This case, should be a String.
     * @throws Exception
     */
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
