package com.swen262.personalLibrary;

import com.swen262.Main;
import com.swen262.Song;
import com.swen262.database.Database;

public class AddSongByGUID implements Action{
    private PersonalLibrary library;

    public AddSongByGUID(PersonalLibrary library){
        this.library = library;
    }

    @Override
    public void performAction(Object o){
        if(o instanceof String){
            String GUID = (String) o;
            Database db = Main.getDB();
            Song song = db.searchSongByGUID(GUID);
            library.addSong(song);
        }
    }
}
