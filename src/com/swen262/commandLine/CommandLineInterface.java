package com.swen262.commandLine;

import com.swen262.Song;
import com.swen262.personalLibrary.*;

import java.util.Scanner;

public class CommandLineInterface {

    private Action addByGUIDAction;
    private Action removeByGUIDAction;
    private RateByGUID rateByGUIDAction;
    private PersonalLibrary library;

    private Mode currentMode;

    private boolean running;

    public CommandLineInterface() {
        addByGUIDAction = new AddByGUID();
        removeByGUIDAction = new RemoveByGUID();
        rateByGUIDAction = new RateByGUID();

        currentMode = new DefaultMode(this);

        running = true;
    }

    protected void addByGUID(String GUID) throws Exception {
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
