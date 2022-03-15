package com.swen262.librarySearches;

import java.util.LinkedList;

/**
 * An interface to be inherited by a concrete strategy to allow
 * for searching of the PersonalLibrary for Artists, Releases, and Songs
 * given query information
 */
public interface LibrarySongSearcher<E> {
    LinkedList<E> algorithm(String query);
}
