package com.swen262.librarySearches;

import java.util.LinkedList;

public interface LibrarySongSearcher<E> {
    public LinkedList<E> algorithm(String query);
}
