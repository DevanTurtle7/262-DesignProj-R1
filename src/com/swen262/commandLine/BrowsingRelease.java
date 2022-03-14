package com.swen262.commandLine;

import com.swen262.Release;

public class BrowsingRelease extends Mode {
    public BrowsingRelease(CommandLineInterface commandLineInterface, Release release) {
        super(commandLineInterface);

        System.out.println("browsing " + release.getTitle());
    }

    @Override
    protected void listCommands() {

    }

    @Override
    protected void handleInput(String input) {

    }
}
