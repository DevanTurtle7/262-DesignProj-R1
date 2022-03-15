package com.swen262.view;

import com.swen262.DBSearches.DBSearcher;
import com.swen262.librarySearches.LibrarySearcher;
import com.swen262.personalLibrary.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class CommandLineInterface {

    private final AddByGUID addByGUIDAction;
    private final Action removeByGUIDAction;
    private final RateByGUID rateByGUIDAction;
    private final Action saveLibraryAction;
    private final PersonalLibrary library;

    private Mode currentMode;
    private DBSearcher dbSearchStrategy;
    private LibrarySearcher libSearchStrategy;

    private boolean running;

    public CommandLineInterface() {
        addByGUIDAction = new AddByGUID();
        removeByGUIDAction = new RemoveByGUID();
        rateByGUIDAction = new RateByGUID();
        saveLibraryAction = new SaveLibrary();
        library = PersonalLibrary.getActiveInstance();

        currentMode = new DefaultMode(this);

        running = true;

        outputMessage("\n=================================");
        outputMessage("            Welcome to           ");
        outputMessage("  The Muze Music Library System  ");
        outputMessage("=================================\n");
    }

    protected void saveLibrary() throws Exception {
        saveLibraryAction.performAction(library);
    }

    protected void setDBSearchStrategy(DBSearcher strategy) {
        this.dbSearchStrategy = strategy;
    }

    protected void setLibSearchStrategy(LibrarySearcher strategy) {
        this.libSearchStrategy = strategy;
    }

    protected LinkedList searchLibrary(String query) {
        LinkedList results = libSearchStrategy.algorithm(query);
        return results;
    }

    protected LinkedList searchDatabase(String query) {
        LinkedList results = dbSearchStrategy.algorithm(query);
        return results;
    }

    protected void addByGUID(String GUID, LocalDate date) throws Exception {
        addByGUIDAction.setDate(date);
        addByGUIDAction.performAction(GUID);
    }

    protected void removeByGUID(String GUID) throws Exception {
        removeByGUIDAction.performAction(GUID);
    }

    protected void outputMessage(String message) {
        System.out.println(message);
    }

    private void handleInput(String input) {
        currentMode.handleInput(input);
    }

    protected void quit() {
        running = false;
    }

    protected void setMode(Mode newMode) {
        this.currentMode = newMode;
    }

    protected void rateByGUID(String GUID, int rating) throws Exception {
        rateByGUIDAction.setRating(rating);
        rateByGUIDAction.performAction(GUID);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = " ";

        while (running) {
            System.out.print(">> ");

            input = scanner.nextLine();
            input = input.strip();
            input = input.toLowerCase();

            handleInput(input);
        }
    }
}