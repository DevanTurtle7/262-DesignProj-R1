package com.swen262.DBSearches;

import java.util.LinkedList;

import com.swen262.database.Database;

public interface SongSearcher<E> {
    public LinkedList<E> algorithm(String query, Database db);
}
