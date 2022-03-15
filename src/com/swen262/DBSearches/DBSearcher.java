package com.swen262.DBSearches;

import java.util.LinkedList;

/**
 * An interface to be inherited by a concrete strategy to allow
 * for searching of the Database for Artists, Releases, and Songs
 * given query information
 */
public interface DBSearcher<E> {
    LinkedList<E> algorithm(String query);
}
