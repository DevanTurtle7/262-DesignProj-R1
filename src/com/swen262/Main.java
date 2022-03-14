package com.swen262;

import org.apache.commons.cli.*;

import java.util.Date;
import java.util.LinkedHashSet;

import com.swen262.commandLine.CommandLineInterface;

public class Main {

    private static Database db;
    private static String song_path;
    private static String release_path;
    private static String artist_path;

    public static void main(String[] args) {
        db = new Database(song_path,release_path,artist_path);

        CommandLineInterface cli = new CommandLineInterface();
        cli.run();

    }
}
