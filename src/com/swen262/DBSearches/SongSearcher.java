package com.swen262.searches;

import java.util.LinkedList;

import com.swen262.database.Database;

public interface SongSearcher<E> {
    public LinkedList<E> algorithm(String query, Database db);
}