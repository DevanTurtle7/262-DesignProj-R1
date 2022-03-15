/**
 * A mode of the command line interface where all the artists that have songs or releases saved
 * in the library are displays. The user can choose to browse an artist.
 *
 * @author Devan Kavalchek
 */

package com.swen262.view;

import com.swen262.model.Artist;
import com.swen262.personalLibrary.PersonalLibrary;
import com.swen262.util.Formatter;

import java.util.HashMap;
import java.util.HashSet;

public class ChoosingArtist extends Mode {

    private final HashMap<String, Artist> artistLibrary;
    private final PersonalLibrary library;

    /**
     * The constructor
     *
     * @param commandLineInterface The command line interface
     */
    public ChoosingArtist(CommandLineInterface commandLineInterface) {
        super(commandLineInterface);

        // Get all the artists in the library
        artistLibrary = new HashMap<>();
        library = PersonalLibrary.getActiveInstance();
        HashSet<Artist> artists = library.getArtists();

        for (Artist artist : artists) {
            String lowercaseName = artist.getName().toLowerCase();
            artistLibrary.put(lowercaseName, artist);
        }

        // Display all the artists
        printArtists();
    }

    /**
     * Displays all the artists in the users library
     */
    private void printArtists() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        HashSet<Artist> artists = library.getArtists();

        // Check that the library is not empty
        if (artists.size() > 0) {
            // Display the header
            String[] header = {
                    "==========================",
                    "         ARTISTS          ",
                    "==========================",
            };

            for (String line : header) {
                commandLineInterface.outputMessage(line);
            }

            // Display each artist
            for (Artist artist : artists) {
                // Get the artist data
                String name = artist.getName();
                String disambiguation = artist.getType();
                int duration = library.getDurationFromArtist(artist);

                // Display the artist data
                commandLineInterface.outputMessage(name);
                commandLineInterface.outputMessage("\tType: " + disambiguation);
                commandLineInterface.outputMessage("\tDuration of Songs: " + Formatter.formatDuration(duration));
            }

            commandLineInterface.outputMessage("\nEnter artists name to browse or esc to exit...");
        } else {
            commandLineInterface.outputMessage("There are no artists in your library.");
        }
    }

    @Override
    protected void listCommands() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String[] message = {
                "==========================",
                "         COMMANDS         ",
                "==========================",
                "Enter artist name to browse their songs and releases.",
                "ls",
                "   Lists the artists in your library.",
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
            printArtists();
        } else if (command.equals("esc")) {
            // Back out to the default page
            commandLineInterface.setMode(new DefaultMode(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else if (command.equals("help")) {
            listCommands();
        } else {
            String artistName = input.toLowerCase();

            // Check if the users string matches any of the artists
            if (artistLibrary.containsKey(artistName)) {
                Artist artist = artistLibrary.get(artistName);
                // Browse that artist
                commandLineInterface.setMode(new BrowsingArtist(commandLineInterface, artist));
            } else {
                // User did not enter a valid artist name. The command is unknown.
                this.unknownCommand();
            }
        }
    }
}
