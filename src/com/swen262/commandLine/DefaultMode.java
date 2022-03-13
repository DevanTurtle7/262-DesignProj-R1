package com.swen262.commandLine;

public class DefaultMode extends Mode {

    public DefaultMode(CommandLineInterface commandLineInterface) {
        super(commandLineInterface);
    }

    @Override
    protected void listCommands() {
        String[] message = {
                "==========================",
                "         COMMANDS         ",
                "==========================",
                "search [cat] [attr]",
                "   Searches your personal library.",
                "   [cat]: The type of object you're searching for (artist, song, release).",
                "   [attr]: The attribute you're searching by (name, type, title, duration, rating, artist).",
        };

        for (String line : message) {
            System.out.println(line);
        }
    }

    @Override
    protected void handleInput(String input) {
        String[] tokens = input.split(" ");
        String command = tokens[0];

        if (command.equals("search")) {

        } else if (command.equals("add")) {

        } else if (command.equals("remove")) {

        } else {
            listCommands();
        }
    }
}
