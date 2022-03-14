package com.swen262.commandLine;

import com.swen262.personalLibrary.AddSongByGUID;
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
                    AddSongByGUID addSong = new AddSongByGUID(library);

                    addSong.performAction(GUID);
                    int numSongs = library.getSongCount();
                    commandLineInterface.outputMessage("Successfully added song. Library now has " + numSongs + " songs.");
                } catch (Exception e) {
                    commandLineInterface.outputMessage("Error adding song");
                }
            }
        } else if (command.equals("remove")) {
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
