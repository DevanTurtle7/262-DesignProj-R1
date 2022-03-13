package com.swen262.commandLine;

import com.swen262.Release;
import com.swen262.Song;
import com.swen262.personalLibrary.*;

import java.util.Scanner;

public class CommandLineInterface {

    private Action addSongAction;
    private Action removeSongAction;
    private Action addReleaseAction;
    private Action removeReleaseAction;
    private PersonalLibrary library;

    private Mode currentMode;

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

    protected void outputMessage(String message) {
        System.out.println(message);
    }

    private void handleInput(String input) {
        currentMode.handleInput(input);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = " ";

        while (!input.equals("")) {
            input = scanner.nextLine();
            input = input.strip();
            handleInput(input);
        }
    }
}
