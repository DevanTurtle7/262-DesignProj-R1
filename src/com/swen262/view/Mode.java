/**
 * Defines some basic behaviour that a CLI state has
 *
 * @author Devan Kavalchek
 */

package com.swen262.view;

public abstract class Mode {
    private final CommandLineInterface commandLineInterface;

    public Mode(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    /**
     * A getter for the command line interface
     * @return
     */
    protected CommandLineInterface getCommandLineInterface() {
        return commandLineInterface;
    }

    /**
     * Displays a message to the user that they entered an unknown command
     */
    protected void unknownCommand() {
        commandLineInterface.outputMessage("Unknown command. Use the 'help' command to list all commands.");
    }

    /**
     * List all of the possible commands
     */
    protected abstract void listCommands();

    /**
     * Handles the users input
     * @param input The input from the command line interface
     */
    protected abstract void handleInput(String input);
}
