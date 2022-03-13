package com.swen262.commandLine;

import com.swen262.Artist;
import com.swen262.personalLibrary.PersonalLibrary;

import java.util.HashSet;

public class ChoosingArtist extends Mode {

    public ChoosingArtist(CommandLineInterface commandLineInterface) {
        super(commandLineInterface);
        printArtists();
    }

    private void printArtists() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        PersonalLibrary library = commandLineInterface.getPersonalLibrary();
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
                commandLineInterface.outputMessage(artist.toString());
            }

            commandLineInterface.outputMessage("Enter artists name to browse or esc to exit...");
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
            // TODO: GET ARTIST
            this.unknownCommand();
        }
    }
}
