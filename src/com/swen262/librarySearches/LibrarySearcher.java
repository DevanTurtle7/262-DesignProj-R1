package com.swen262.librarySearches;

import java.util.LinkedList;

/**
 * An interface to be inherited by a concrete strategy to allow
 * for searching of the PersonalLibrary for Artists, Releases, and Songs
 * given query information
 */
public interface LibrarySearcher<E> {
    /**
     * An algorithm to return a LinkedList of the generic type used by
     * the concrete strategy.
     * @param query The query used to search the library
     * @return a LinkedList of the generic type used by the concrete strategy
     */
    LinkedList<E> algorithm(String query);
}
