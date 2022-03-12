package com.swen262;

public class AddSong implements Action{
    private PersonalLibrary library;

    public AddSong(PersonalLibrary library){
        this.library = library;
    }

    @Override
    public void performAction(Object o){
        if(o instanceof Song){
            Song newSong = (Song) o;
            library.addSong(newSong);
        }
    }
}
