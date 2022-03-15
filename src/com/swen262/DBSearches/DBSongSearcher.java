package com.swen262.DBSearches;

import java.util.LinkedList;

public interface DBSongSearcher<E> {
    public LinkedList<E> algorithm(String query);
}
