package com.swen262.DBSearches;

import java.util.LinkedList;

/**
 * An interface to be inherited by a concrete strategy to allow
 * for searching of the Database for Artists, Releases, and Songs
 * given query information
 */
public interface DBSearcher<E> {
    /**
     * An algorithm to return a LinkedList of the generic type used by
     * the concrete strategy.
     * @param query The query used to search the database
     * @return a LinkedList of the generic type used by the concrete strategy
     */
    LinkedList<E> algorithm(String query);
}
