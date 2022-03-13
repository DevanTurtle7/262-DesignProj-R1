package com.swen262.commandLine;

import com.swen262.Release;
import com.swen262.Song;
import com.swen262.personalLibrary.*;

public class CommandLineInterface {

    private Action addSongAction;
    private Action removeSongAction;
    private Action addReleaseAction;
    private Action removeReleaseAction;
    private PersonalLibrary library;

    public CommandLineInterface() {
        library = PersonalLibrary.loadPersonalLibrary();

        addSongAction = new AddSong(library);
        removeSongAction = new RemoveSong(library);
        addReleaseAction = new AddRelease(library);
        removeReleaseAction = new RemoveRelease(library);
    }

    protected void addSong(Song song) {
        addSongAction.performAction(song);
    }

    protected void removeSong(Song song) {
        removeSongAction.performAction(song);
    }

    protected void addRelease(Release release) {
        addReleaseAction.performAction(release);
    }

    protected void removeRelease(Release release) {
        removeReleaseAction.performAction(release);
    }

    public void run() {

    }
}
