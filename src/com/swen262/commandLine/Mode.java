package com.swen262.commandLine;

public abstract class Mode {
    private CommandLineInterface commandLineInterface;

    public Mode(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    protected abstract void listCommands();
    protected abstract void handleInput(String input);
}
