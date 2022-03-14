package com.swen262;

import com.swen262.database.Database;

import com.swen262.commandLine.CommandLineInterface;

public class Main {

    private static Database db;

    public static void main(String[] args) {
        db = new Database();

        CommandLineInterface cli = new CommandLineInterface();
        cli.run();
    }
}
