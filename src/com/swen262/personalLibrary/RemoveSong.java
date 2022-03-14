package com.swen262.personalLibrary;

import com.swen262.Song;

public class RemoveSong implements Action{
    private PersonalLibrary library;

    public RemoveSong(PersonalLibrary library){
        this.library = library;
    }

    @Override
    public void performAction(Object o) {
        if(o instanceof Song){
            Song newSong = (Song) o;
            library.removeSong(newSong);
        }
    }
}
