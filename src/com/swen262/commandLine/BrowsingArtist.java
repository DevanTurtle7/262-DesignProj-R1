package com.swen262.commandLine;

import com.swen262.Artist;
import com.swen262.Release;
import com.swen262.Song;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.HashSet;
import java.util.LinkedList;

public class BrowsingArtist extends Mode {
    private PersonalLibrary library;
    private Artist artist;
    private HashSet<Song> songs;
    private HashSet<Release> releases;

    private final int MS_IN_HOUR = 3600000;
    private final int MS_IN_MINUTE = 60000;
    private final int MS_IN_SECOND = 1000;

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

        String duration = minutes + "m" + seconds + "s" + milliseconds + "ms";

        if (hours > 0) {
            duration = hours + "h" + duration;
        }

        return duration;
    }

    private void outputSong(Song song) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = song.getTitle();
        String duration = formatDuration(song.getDuration());
        String rating = song.getRating() + "";

        commandLineInterface.outputMessage(title + " " + duration + " " + rating);
    }

    private void outputRelease(Release release) {
        //Releases will be displayed including title, date, media, average rating, total duration of all tracks.
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String title = release.getTitle();
        String date = release.getIssueDate().toString();
        //TODO: Add media??? maybe it means medium...
        String medium = release.getMedium();
        String averageRating = release.getRating() + "";
        String duration = formatDuration(release.getDuration());

        commandLineInterface.outputMessage(title + " " + date + " " + medium + " " + averageRating + " " + duration);
    }

    private void listContent() {
        for (Song song : songs) {
            outputSong(song);
        }

        for (Release release : releases) {
            outputRelease(release);
        }
    }

    @Override
    protected void listCommands() {

    }

    @Override
    protected void handleInput(String input) {

    }
}
