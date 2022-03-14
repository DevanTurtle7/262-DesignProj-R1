package com.swen262.commandLine;

import com.swen262.Artist;

public class BrowsingArtist extends Mode {

    public BrowsingArtist(CommandLineInterface commandLineInterface, Artist artist) {
        super(commandLineInterface);

        System.out.println("browsing " + artist.getName());
    }

    @Override
    protected void listCommands() {

    }

    @Override
    protected void handleInput(String input) {

    }
}
