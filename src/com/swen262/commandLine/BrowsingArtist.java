package com.swen262.commandLine;

import com.swen262.Artist;
import com.swen262.Release;
import com.swen262.Song;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.HashSet;
import java.util.Locale;

public class BrowsingArtist extends Mode {
    private PersonalLibrary library;
    private Artist artist;
    private HashSet<Song> songs;
    private HashSet<Release> releases;

    private final int MS_IN_HOUR = 3600000;
    private final int MS_IN_MINUTE = 60000;
    private final int MS_IN_SECOND = 1000;

    private final int NUM_SPACES = 5;

    public BrowsingArtist(CommandLineInterface commandLineInterface, Artist artist) {
        super(commandLineInterface);

        library = PersonalLibrary.getActiveInstance();

        this.songs = library.getSongsFromArtist(artist);
        this.releases = library.getReleasesFromArtist(artist);
        this.artist = artist;

        listContent();
    }

    private String formatDuration(int ms) {
        int remainingMS = ms;

        int hours = Math.floorDiv(remainingMS, MS_IN_HOUR);
        remainingMS -= hours * MS_IN_HOUR;
        int minutes = Math.floorDiv(remainingMS, MS_IN_MINUTE);
        remainingMS -= minutes * MS_IN_MINUTE;
        int seconds = Math.floorDiv(remainingMS, MS_IN_SECOND);
        remainingMS -= seconds * MS_IN_SECOND;
        int milliseconds = remainingMS;

        String duration = minutes + "m " + seconds + "s " + milliseconds + "ms";

        if (hours > 0) {
            duration = hours + "h " + duration;
        }

        return duration;
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
        String duration = formatDuration(song.getDuration());
        String rating = song.getRating() + "";

        commandLineInterface.outputMessage(title);
        commandLineInterface.outputMessage("\tDuration: " + duration);
        commandLineInterface.outputMessage("Rating: " + rating + " / 5 stars");
    }

    private void outputRelease(Release release) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = release.getTitle();
        String date = release.getIssueDate().toString();
        //TODO: Add media??? maybe it means medium...
        String medium = release.getMedium();
        String averageRating = release.getRating() + "";
        String duration = formatDuration(release.getDuration());

        commandLineInterface.outputMessage(title);
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
            commandLineInterface.setMode(new DefaultMode(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else {
            // TODO: go to release
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
