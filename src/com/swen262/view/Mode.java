package com.swen262.view;

public abstract class Mode {
    private final CommandLineInterface commandLineInterface;

    public Mode(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    protected CommandLineInterface getCommandLineInterface() {
        return commandLineInterface;
    }

    protected void unknownCommand() {
        commandLineInterface.outputMessage("Unknown command. Use the 'help' command to list all commands.");
    }

    protected abstract void listCommands();

    protected abstract void handleInput(String input);
}
