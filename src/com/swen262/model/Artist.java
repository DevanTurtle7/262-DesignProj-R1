package com.swen262.model;

public class Artist implements Comparable<Artist> {
    private final String GUID;
    private final String name;
    private final String type;
    private final int rating;

    public Artist(String name, String type, int rating, String GUID) {
        this.GUID = GUID;
        this.name = name;
        this.type = type;
        this.rating = rating;
    }

    public Artist(String GUID, String name, String type) {
        this(name, type, 0, GUID);
    }

    public String getGUID() {
        return this.GUID;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return (name + ", type: " + type + ", rating: " + rating);
    }

    @Override
    //Credit https://www.geeksforgeeks.org/compare-two-strings-in-java/
    public int compareTo(Artist o) {
        int l1 = name.length();
        int l2 = o.name.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = name.charAt(i);
            int str2_ch = o.name.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        } else {
            return 0;
        }
    }
}
