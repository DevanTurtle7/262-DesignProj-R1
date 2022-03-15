/**
 * A mode of the command line interface where the releases and artists from a given artist in
 * the personal library are displayed.
 *
 * @author Devan Kavalchek
 */

package com.swen262.view;

import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;
import com.swen262.util.Formatter;

import java.util.HashSet;

public class BrowsingArtist extends Mode {
    private final PersonalLibrary library;
    private final Artist artist;
    private final HashSet<Song> songs;
    private final HashSet<Release> releases;

    private final int NUM_SPACES = 5;

    /**
     * The constructor
     * @param commandLineInterface The command line interface object
     * @param artist The artist whose content is being displayed
     */
    public BrowsingArtist(CommandLineInterface commandLineInterface, Artist artist) {
        super(commandLineInterface);

        library = PersonalLibrary.getActiveInstance();

        this.songs = library.getSongsFromArtist(artist);
        this.releases = library.getReleasesFromArtist(artist);
        this.artist = artist;

        listContent();
    }

    /**
     * Outputs a stylized header for the artists name
     */
    private void outputArtistName() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String name = artist.getName();
        int nameLength = name.length();
        String bar = "";
        String spaces = "";

        // Build the strings
        for (int i = 0; i < (NUM_SPACES * 2) + nameLength; i++) {
            bar += "=";
        }

        for (int i = 0; i < NUM_SPACES; i++) {
            spaces += " ";
        }

        // Display the strings
        commandLineInterface.outputMessage(bar);
        commandLineInterface.outputMessage(spaces + name + spaces);
        commandLineInterface.outputMessage(bar);
    }

    /**
     * Displays data of a song
     *
     * @param song The song being displayed
     */
    private void outputSong(Song song) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        // Get the data from the song
        String title = song.getTitle();
        String duration = Formatter.formatDuration(song.getDuration());
        String rating = song.getRating() + "";
        String GUID = song.getGUID();

        // Display the data
        commandLineInterface.outputMessage(title);
        commandLineInterface.outputMessage("\tGUID: " + GUID);
        commandLineInterface.outputMessage("\tDuration: " + duration);
        commandLineInterface.outputMessage("\tRating: " + rating + " / 5 stars");
    }

    /**
     * Displays data of a release
     *
     * @param release The release being displayed
     */
    private void outputRelease(Release release) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        // Get the data from the song
        String title = release.getTitle();
        String date = release.getIssueDate().toString();
        String medium = release.getMedium();
        String averageRating = release.getRating() + "";
        String duration = Formatter.formatDuration(release.getDuration());
        String GUID = release.getGUID();

        // Display the data
        commandLineInterface.outputMessage(title);
        commandLineInterface.outputMessage(title);
        commandLineInterface.outputMessage("\tGUID: " + GUID);
        commandLineInterface.outputMessage("\tIssue Date: " + date);
        commandLineInterface.outputMessage("\tMedium: " + medium);
        commandLineInterface.outputMessage("\tAverage Rating: " + averageRating + " / 5 stars");
        commandLineInterface.outputMessage("\tDuration: " + duration);
    }

    /**
     * Displays all the singles and releases in the personal library
     */
    private void listContent() {
        CommandLineInterface commandLineInterface = getCommandLineInterface();

        // Display the header
        outputArtistName();

        // Displays the singles
        commandLineInterface.outputMessage("Singles");
        commandLineInterface.outputMessage("-----------");
        if (songs.size() > 0) {
            for (Song song : songs) {
                outputSong(song);
            }
        } else {
            commandLineInterface.outputMessage("There are no singles in your library.");
        }

        // Display the releases
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
        // Parse the input
        String[] args = input.split(" ");
        String command = args[0];
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();

        // Handle the command
        if (command.equals("ls")) {
            listContent();
        } else if (command.equals("help")) {
            listCommands();
        } else if (command.equals("esc")) {
            // Back out to the choosing artist page
            commandLineInterface.setMode(new ChoosingArtist(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else {
            Release found = null;
            input = input.toLowerCase();

            // Attempt to find a release matching the user's input
            for (Release release : releases) {
                if (found == null) {
                    String current = release.getTitle().toLowerCase();

                    if (input.equals(current)) {
                        found = release;
                    }
                }
            }

            if (found != null) {
                // Release was found. Browse that release
                commandLineInterface.setMode(new BrowsingRelease(commandLineInterface, found));
            } else {
                // User did not enter a valid release. Command is unknown.
                this.unknownCommand();
            }
        }
    }
}
