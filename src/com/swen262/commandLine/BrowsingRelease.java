package com.swen262.commandLine;

import com.swen262.Release;
import com.swen262.Song;

import java.util.LinkedList;

public class BrowsingRelease extends Mode {

    private Release release;

    private final int MS_IN_HOUR = 3600000;
    private final int MS_IN_MINUTE = 60000;
    private final int MS_IN_SECOND = 1000;

    private final int NUM_SPACES = 5;

    public BrowsingRelease(CommandLineInterface commandLineInterface, Release release) {
        super(commandLineInterface);

        this.release = release;

        listTracks();
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

    private void outputReleaseTitle() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = release.getTitle();
        int titleLength = title.length();
        String bar = "";
        String spaces = "";

        for (int i = 0; i < (NUM_SPACES * 2) + titleLength; i++) {
            bar += "=";
        }

        for (int i = 0; i < NUM_SPACES; i++) {
            spaces += " ";
        }

        commandLineInterface.outputMessage(bar);
        commandLineInterface.outputMessage(spaces + title + spaces);
        commandLineInterface.outputMessage(bar);
    }

    private void outputSong(Song song, int index) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = song.getTitle();
        String duration = formatDuration(song.getDuration());
        String rating = song.getRating() + "";
        String GUID = song.getGUID();

        commandLineInterface.outputMessage(index + ") " + title);
        commandLineInterface.outputMessage("\tGUID: " + GUID);
        commandLineInterface.outputMessage("\tDuration: " + duration);
        commandLineInterface.outputMessage("\tRating: " + rating + " / 5 stars");
    }

    private void listTracks() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        outputReleaseTitle();

        commandLineInterface.outputMessage("By: " + release.getArtist().getName());
        commandLineInterface.outputMessage("Duration: " + formatDuration(release.getDuration()));
        commandLineInterface.outputMessage("Released: " + release.getIssueDate());
        commandLineInterface.outputMessage("Rating: " + release.getRating());
        commandLineInterface.outputMessage("\nTracks");
        commandLineInterface.outputMessage("-----------");

        LinkedList<Song> songs = release.getTracks();

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            outputSong(song, i + 1);
        }
    }

    @Override
    protected void listCommands() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String[] message = {
                "==========================",
                "         COMMANDS         ",
                "==========================",
                "ls",
                "   Lists the tracks in the release.",
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
            listTracks();
        } else if (command.equals("help")) {
            listCommands();
        } else if (command.equals("esc")) {
            commandLineInterface.setMode(new BrowsingArtist(commandLineInterface, release.getArtist()));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else {
            this.unknownCommand();
        }
    }
}
