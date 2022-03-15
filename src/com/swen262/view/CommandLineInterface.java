/**
 * The main view that interacts with the user. Accepts user input and handles it. Outputs messages
 * in response to the input.
 *
 * @author Devan Kavalchek
 */

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

    /**
     * The constructor
     */
    public CommandLineInterface() {
        // Create the actions
        addByGUIDAction = new AddByGUID();
        removeByGUIDAction = new RemoveByGUID();
        rateByGUIDAction = new RateByGUID();
        saveLibraryAction = new SaveLibrary();
        library = PersonalLibrary.getActiveInstance();

        // Set the state
        currentMode = new DefaultMode(this);

        running = true;

        // Output the welcome message
        outputMessage("\n=================================");
        outputMessage("            Welcome to           ");
        outputMessage("  The Muze Music Library System  ");
        outputMessage("=================================\n");
    }

    /**
     * Saves the personal library. Calls the saveLibrary action
     * @throws Exception
     */
    protected void saveLibrary() throws Exception {
        saveLibraryAction.performAction(library);
    }

    /**
     * Sets the database search strategy
     *
     * @param strategy The new search strategy
     */
    protected void setDBSearchStrategy(DBSearcher strategy) {
        this.dbSearchStrategy = strategy;
    }

    /**
     * Sets the library search strategy
     *
     * @param strategy The new search strategy
     */
    protected void setLibSearchStrategy(LibrarySearcher strategy) {
        this.libSearchStrategy = strategy;
    }

    /**
     * Searches the personal library. Uses the current library search strategy.
     *
     * @param query The search query
     * @return A list of results
     */
    protected LinkedList searchLibrary(String query) {
        LinkedList results = libSearchStrategy.algorithm(query);
        return results;
    }

    /**
     * Searches the database library. Uses the current database search strategy.
     *
     * @param query The search query
     * @return A list of results
     */
    protected LinkedList searchDatabase(String query) {
        LinkedList results = dbSearchStrategy.algorithm(query);
        return results;
    }

    /**
     * Adds a song by a GUID
     * @param GUID The GUID of the song or release being added
     * @param date The date that the song or release was added to the library
     * @throws Exception Throws a GUIDNotFoundException if a song or release was not found
     */
    protected void addByGUID(String GUID, LocalDate date) throws Exception {
        addByGUIDAction.setDate(date);
        addByGUIDAction.performAction(GUID);
    }

    /**
     * Removes a song or release with the given GUID
     * @param GUID The GUID of the song or release being removed
     * @throws Exception Throws a GUIDNotFoundException if a song or release was not found
     */
    protected void removeByGUID(String GUID) throws Exception {
        removeByGUIDAction.performAction(GUID);
    }

    /**
     * Displays a message to the user
     * @param message The message being displayed
     */
    protected void outputMessage(String message) {
        System.out.println(message);
    }

    /**
     * Passes the user's input to be handled by the current state
     * @param input The input from the command line interface
     */
    private void handleInput(String input) {
        currentMode.handleInput(input);
    }

    /**
     * Quits the program
     */
    protected void quit() {
        running = false;
    }

    /**
     * Sets the state
     * @param newMode The new state of the CLI
     */
    protected void setMode(Mode newMode) {
        this.currentMode = newMode;
    }

    /**
     * Rates a song or release with a given GUID
     * @param GUID The GUID of the song or release
     * @param rating The rating that is being given to the song or release
     * @throws Exception Throws a GUIDNotFoundException if a song or release was not found
     */
    protected void rateByGUID(String GUID, int rating) throws Exception {
        rateByGUIDAction.setRating(rating);
        rateByGUIDAction.performAction(GUID);
    }

    /**
     * Starts the command line interface
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = " ";

        while (running) {
            System.out.print(">> ");

            // Do some basic cleanup of the input
            input = scanner.nextLine();
            input = input.strip();
            input = input.toLowerCase();

            // Pass the input to the current state
            handleInput(input);
        }
    }
}
