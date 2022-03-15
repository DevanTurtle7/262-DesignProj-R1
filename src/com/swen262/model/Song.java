package com.swen262.model;

public class Song implements Comparable<Song> {
    private final String title;
    private final Artist artist;
    private final int duration;
    private int rating;
    private final String GUID;
    private Release release;

    /**
     * Creates a new object representing a Song
     * @param title string representation of the Song title
     * @param artist the Song's artist in the form of an Artist object
     * @param duration the Song's duration
     * @param rating the user rating of the Song
     * @param GUID unique string to represent the Song
     */
    public Song(String title, Artist artist, int duration, int rating, String GUID) {
        this.GUID = GUID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.rating = rating;
    }

    /**
     * Creates a new object representing a Song with a default rating of zero
     * @param title string representation of the Song title
     * @param artist the Song's artist in the form of an Artist object
     * @param duration the Song's duration
     * @param GUID unique string to represent the Song
     */
    public Song(String title, Artist artist, int duration, String GUID) {
        this(title, artist, duration, 0, GUID);
    }

    /**
     * returns the title stored in this instance
     * @return a string representation of title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the release stored in this instance
     * @param release the Release being stored
     */
    public void setRelease(Release release) {
        this.release = release;
    }

    /**
     * returns the release stored in this instance,
     * if null no release has been set yet
     * @return a Release object
     */
    public Release getRelease() {
        return release;
    }

    /**
     * returns the artist stored in this instance
     * @return an Artist object
     */
    public Artist getArtist() {
        return this.artist;
    }

    /**
     * returns the duration stored in this instance
     * @return an integer representation of duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * returns the rating stored in this instance, 
     * if 0 the user has not yet provided a rating
     * @return an integer representation of rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * returns the GUID stored in this instance
     * @return a string representation of GUID
     */
    public String getGUID() {
        return this.GUID;
    }

    /**
     * Sets the rating of the Song
     * @param rate the rating given to the Song by a user input
     */
    public void rate(int rate) {
        if (rate <= 5 && rate >= 1) {
            this.rating = rate;
        } else {
            System.out.println("Rating must be between 1 to 5 stars.");
        }
    }

    /**
     * creates a string representation of the Song class
     * @return a string representation of this instance
     */
    @Override
    public String toString() {
        return (title + ", by: " + artist.getName());
    }

    /**
     * compares a Song object to this instance of Song
     * @return an integer of 0 if the objects are equal, and an integer not equal to 0 if they are not equal
     */
    @Override
    public int compareTo(Song o) {
        return this.rating - o.rating;
    }
}
