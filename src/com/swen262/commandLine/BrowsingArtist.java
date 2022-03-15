package com.swen262.commandLine;

import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;
import com.swen262.util.Formatter;

import java.util.HashSet;

public class BrowsingArtist extends Mode {
    private PersonalLibrary library;
    private Artist artist;
    private HashSet<Song> songs;
    private HashSet<Release> releases;

    private final int NUM_SPACES = 5;

    public BrowsingArtist(CommandLineInterface commandLineInterface, Artist artist) {
        super(commandLineInterface);

        library = PersonalLibrary.getActiveInstance();

        this.songs = library.getSongsFromArtist(artist);
        this.releases = library.getReleasesFromArtist(artist);
        this.artist = artist;

        listContent();
    }

    private void outputArtistName() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String name = artist.getName();
        int nameLength = name.length();
        String bar = "";
        String spaces = "";

        for (int i = 0; i < (NUM_SPACES * 2) + nameLength; i++) {
            bar += "=";
        }

        for (int i = 0; i < NUM_SPACES; i++) {
            spaces += " ";
        }

        commandLineInterface.outputMessage(bar);
        commandLineInterface.outputMessage(spaces + name + spaces);
        commandLineInterface.outputMessage(bar);
    }

    private void outputSong(Song song) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = song.getTitle();
        String duration = Formatter.formatDuration(song.getDuration());
        String rating = song.getRating() + "";
        String GUID = song.getGUID();

        commandLineInterface.outputMessage(title);
        commandLineInterface.outputMessage("\tGUID: " + GUID);
        commandLineInterface.outputMessage("\tDuration: " + duration);
        commandLineInterface.outputMessage("\tRating: " + rating + " / 5 stars");
    }

    private void outputRelease(Release release) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = release.getTitle();
        String date = release.getIssueDate().toString();
        String medium = release.getMedium();
        String averageRating = release.getRating() + "";
        String duration = Formatter.formatDuration(release.getDuration());
        String GUID = release.getGUID();

        commandLineInterface.outputMessage(title);
        commandLineInterface.outputMessage("\tGUID: " + GUID);
        commandLineInterface.outputMessage("\tIssue Date: " + date);
        commandLineInterface.outputMessage("\tMedium: " + medium);
        commandLineInterface.outputMessage("\tAverage Rating: " + averageRating + " / 5 stars");
        commandLineInterface.outputMessage("\tDuration: " + duration);
    }

    private void listContent() {
        CommandLineInterface commandLineInterface = getCommandLineInterface();

        outputArtistName();

        commandLineInterface.outputMessage("Singles");
        commandLineInterface.outputMessage("-----------");
        if (songs.size() > 0) {
            for (Song song : songs) {
                outputSong(song);
            }
        } else {
            commandLineInterface.outputMessage("There are no singles in your library.");
        }

        commandLineInterface.outputMessage("\nReleases");
        commandLineInterface.outputMessage("-----------");
        if (releases.size() > 0) {
            for (Release release : releases) {
                outputRelease(release);
            }
        } else {
            commandLineInterface.outputMessage("There are no releases in your library.");
        }
    }

    @Override
    protected void listCommands() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String[] message = {
                "==========================",
                "         COMMANDS         ",
                "==========================",
                "Enter release name to browse it's songs",
                "ls",
                "   Lists the songs and releases in your library.",
                "esc",
                "   Exits browsing mode.",
                "quit",
                "   Exits the program.",
        };

        for (String line : message) {
            commandLineInterface.outputMessage(line);
        }
    }

    @Override
    protected void handleInput(String input) {
        String[] args = input.split(" ");
        String command = args[0];
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();

        if (command.equals("ls")) {
            listContent();
        } else if (command.equals("help")) {
            listCommands();
        } else if (command.equals("esc")) {
            commandLineInterface.setMode(new ChoosingArtist(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else {
            Release found = null;
            input = input.toLowerCase();

            for (Release release : releases) {
                if (found == null) {
                    String current = release.getTitle().toLowerCase();

                    if (input.equals(current)) {
                        found = release;
                    }
                }
            }

            if (found != null) {
                commandLineInterface.setMode(new BrowsingRelease(commandLineInterface, found));
            } else {
                this.unknownCommand();
            }
        }
    }
}
