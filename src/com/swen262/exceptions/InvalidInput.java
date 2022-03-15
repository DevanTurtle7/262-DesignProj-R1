/**
 * An exception that is thrown when an invalid query is given to a search
 */

package com.swen262.exceptions;

public class InvalidInput extends Exception {
    public InvalidInput(String message) {
        super(message);
    }
}