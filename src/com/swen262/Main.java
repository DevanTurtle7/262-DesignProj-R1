package com.swen262;

import com.swen262.commandLine.CommandLineInterface;
import com.swen262.database.Database;
import com.swen262.personalLibrary.PersonalLibrary;

public class Main {
    public static void main(String[] args) {
        Database db = Database.getActiveInstance();
        PersonalLibrary library = PersonalLibrary.getActiveInstance();
        CommandLineInterface cli = new CommandLineInterface();
        cli.run();
    }
}
