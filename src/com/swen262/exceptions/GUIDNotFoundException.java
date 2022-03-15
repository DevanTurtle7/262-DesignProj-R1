/**
 * An exception that is thrown when a release or song cannot be found with a given GUID
 *
 * @author Devan Kavalchek
 */
package com.swen262.exceptions;

public class GUIDNotFoundException extends Exception {
    public GUIDNotFoundException() {
        super("Item with GUID could not be found");
    }
}
