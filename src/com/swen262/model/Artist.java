package com.swen262.model;

public class Artist implements Comparable<Artist> {
    private final String GUID;
    private final String name;
    private final String type;
    private final int rating;

    /**
     * Creates a new object representing an Artist
     * @param name the Artist's name
     * @param type the type of Artist (person, group, orchestra, etc.)
     * @param rating the Artist's rating
     * @param GUID unique string to represent the Artist
     */
    public Artist(String name, String type, int rating, String GUID) {
        this.GUID = GUID;
        this.name = name;
        this.type = type;
        this.rating = rating;
    }

    /**
     * Creates a new object representing an Artist with a default rating of zero
     * @param name the Artist's name
     * @param type the type of Artist (person, group, orchestra, etc.)
     * @param GUID unique string to represent the Artist
     */
    public Artist(String GUID, String name, String type) {
        this(name, type, 0, GUID);
    }

    /**
     * returns the GUID stored in this instance
     * @return a string representation of GUID
     */
    public String getGUID() {
        return this.GUID;
    }

    /**
     * returns the name stored in this instance
     * @return a string representation of name
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the type stored in this instance
     * @return a string representation of type
     */
    public String getType() {
        return this.type;
    }

    /**
     * returns the rating stored in this instance
     * @return an integer representation of rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * creates a string representation of the Artist class
     * @return a string representation of this instance
     */
    @Override
    public String toString() {
        return (name + ", type: " + type + ", rating: " + rating);
    }

    /**
     * compares an Artist object to this instance of Artist
     * @return an integer of 0 if the objects are equal, and an integer not equal to 0 if they are not equal
     */
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
