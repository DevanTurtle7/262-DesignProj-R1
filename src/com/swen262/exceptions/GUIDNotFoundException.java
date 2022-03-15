package com.swen262.exceptions;

public class GUIDNotFoundException extends Exception {
    public GUIDNotFoundException() {
        super("Item with GUID could not be found");
    }
}
