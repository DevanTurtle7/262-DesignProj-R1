package com.swen262.commandLine;

import com.swen262.exceptions.GUIDNotFoundException;
import com.swen262.personalLibrary.PersonalLibrary;

public class DefaultMode extends Mode {

    public DefaultMode(CommandLineInterface commandLineInterface) {
        super(commandLineInterface);
    }

    @Override
    protected void listCommands() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String[] message = {
                "==========================",
                "         COMMANDS         ",
                "==========================",
                "searchlib [cat] [attr]",
                "   Searches your personal library.",
                "   [cat]: The type of object you're searching for (artist, song, release).",
                "   [attr]: The attribute you're searching by (name, type, title, duration, rating, artist).",
                "searchdb [cat] [attr]",
                "   Searches the database",
                "   [cat]: The type of object you're searching for (artist, song, release).",
                "   [attr]: The attribute you're searching by (name, type, title, duration, rating, artist).",
                "add [guid] <date>",
                "   Adds to your personal library.",
                "   [guid]: The GUID of a song or release you want to add.",
                "   <date>: (OPTIONAL) The date the song or release was acquired. Defaults to the current time.",
                "remove [guid]",
                "   Removes from your personal library.",
                "   [guid]: The GUID of a song or release you want to remove.",
                "browse",
                "   Enters into browsing mode. Displays your personal library.",
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
        PersonalLibrary library = commandLineInterface.getPersonalLibrary();

        if (command.equals("searchlib")) {

        } else if (command.equals("searchdb")) {

        } else if (command.equals("add")) {
            // TODO: ADD DATE
            if (args.length < 2) {
                this.unknownCommand();
            } else {
                try {
                    String GUID = args[1];
                    commandLineInterface.addByGUID(GUID);

                    int numSongs = library.getSongCount();
                    int numReleases = library.getReleaseCount();
                    commandLineInterface.outputMessage("Successfully added. Library now has " + numSongs + " songs and " + numReleases + " releases.");
                } catch (GUIDNotFoundException e) {
                    commandLineInterface.outputMessage("Error adding to library. Could not find song or release with matching GUID.");
                } catch (Exception e) {
                    commandLineInterface.outputMessage("Something went wrong.");
                }
            }
        } else if (command.equals("remove")) {
            try {
                String GUID = args[1];
                commandLineInterface.removeByGUID(GUID);

                int numSongs = library.getSongCount();
                int numReleases = library.getReleaseCount();
                commandLineInterface.outputMessage("Successfully removed. Library now has " + numSongs + " songs and " + numReleases + " releases.");
            } catch (Exception e) {
                commandLineInterface.outputMessage("Something went wrong.");
            }
        } else if (command.equals("browse")) {
            commandLineInterface.setMode(new ChoosingArtist(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else if (command.equals("help")) {
            listCommands();
        } else {
            this.unknownCommand();
        }
    }
}
