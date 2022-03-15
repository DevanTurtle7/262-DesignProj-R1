package com.swen262.personalLibrary;

import com.swen262.model.Song;

public class AddSong implements Action {
    private final PersonalLibrary library;

    public AddSong(PersonalLibrary library) {
        this.library = library;
    }

    @Override
    public void performAction(Object o) {
        if (o instanceof Song) {
            Song newSong = (Song) o;
            library.addSong(newSong);
        }
    }
}
