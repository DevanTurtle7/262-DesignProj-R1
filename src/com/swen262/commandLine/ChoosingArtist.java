package com.swen262.commandLine;

import com.swen262.Artist;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class ChoosingArtist extends Mode {

    private HashMap<String, Artist> artistLibrary;
    private PersonalLibrary library;

    public ChoosingArtist(CommandLineInterface commandLineInterface) {
        super(commandLineInterface);

        artistLibrary = new HashMap<>();
        library = PersonalLibrary.getActiveInstance();
        HashSet<Artist> artists = library.getArtists();

        for (Artist artist : artists) {
            String lowercaseName = artist.getName().toLowerCase();
            artistLibrary.put(lowercaseName, artist);
        }

        printArtists();
    }

    private void printArtists() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        HashSet<Artist> artists = library.getArtists();

        if (artists.size() > 0) {
            String[] header = {
                    "==========================",
                    "         ARTISTS          ",
                    "==========================",
            };

            for (String line : header) {
                commandLineInterface.outputMessage(line);
            }

            for (Artist artist : artists) {
                commandLineInterface.outputMessage(artist.getName());
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
            commandLineInterface.setMode(new DefaultMode(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else if (command.equals("help")) {
            listCommands();
        } else {
            String artistName = input.toLowerCase();

            if (artistLibrary.containsKey(artistName)) {
                Artist artist = artistLibrary.get(artistName);
                commandLineInterface.setMode(new BrowsingArtist(commandLineInterface, artist));
            } else {
                this.unknownCommand();
            }
        }
    }
}
