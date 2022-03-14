package com.swen262.commandLine;

import com.swen262.Song;
import com.swen262.personalLibrary.Action;
import com.swen262.personalLibrary.AddByGUID;
import com.swen262.personalLibrary.PersonalLibrary;
import com.swen262.personalLibrary.RemoveByGUID;

import java.util.Scanner;

public class CommandLineInterface {

    private Action addByGUIDAction;
    private Action removeByGUIDAction;
    private PersonalLibrary library;

    private Mode currentMode;

    private boolean running;

    public CommandLineInterface() {
        library = PersonalLibrary.loadPersonalLibrary();

        addByGUIDAction = new AddByGUID(library);
        removeByGUIDAction = new RemoveByGUID(library);

        currentMode = new DefaultMode(this);

        running = true;
    }

    protected void addByGUID(Song song) throws Exception {
        addByGUIDAction.performAction(song);
    }

    protected void removeByGUID(Song song) throws Exception {
        removeByGUIDAction.performAction(song);
    }

    protected PersonalLibrary getPersonalLibrary() {
        return library;
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
