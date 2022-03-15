package com.swen262.view;

import com.swen262.DBSearches.SearchArtistByName;
import com.swen262.DBSearches.SearchReleaseByArtistGUID;
import com.swen262.DBSearches.SearchReleaseByTitle;
import com.swen262.DBSearches.SearchReleaseByTrackGUID;
import com.swen262.DBSearches.SearchReleaseByTrackName;
import com.swen262.DBSearches.SearchSongByTitle;
import com.swen262.DBSearches.*;
import com.swen262.exceptions.GUIDNotFoundException;
import com.swen262.librarySearches.*;
import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;
import com.swen262.personalLibrary.PersonalLibrary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;

public class DefaultMode extends Mode {

    private final PersonalLibrary library;

    public DefaultMode(CommandLineInterface commandLineInterface) {
        super(commandLineInterface);

        library = PersonalLibrary.getActiveInstance();
    }

    private void printResultsHeader(LinkedList results) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        int numResults = results.size();

        if (numResults == 0) {
            commandLineInterface.outputMessage("No results found.");
        } else {
            commandLineInterface.outputMessage("Results (" + numResults + ")");
            commandLineInterface.outputMessage("---------------");
        }
    }

    private void printArtistResults(LinkedList<Artist> results) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        printResultsHeader(results);

        for (Artist artist : results) {
            commandLineInterface.outputMessage(artist.toString());
        }
    }

    private void printReleaseResults(LinkedList<Release> results) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        printResultsHeader(results);

        for (Release release : results) {
            commandLineInterface.outputMessage(release.toString());
        }
    }

    private void printSongResults(LinkedList<Song> results) {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        printResultsHeader(results);

        for (Song song : results) {
            commandLineInterface.outputMessage(song.toString());
        }
    }

    @Override
    protected void listCommands() {
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();
        String[] message = {
                "==========================",
                "         COMMANDS         ",
                "==========================",
                "searchlib [cat] [attr] [query]",
                "   Searches your personal library.",
                "   [cat]: The type of object you're searching for (artist, song, release).",
                "   [attr]: The attribute you're searching by (name, type, title, duration, rating, artist).",
                "   [query]: Your search query.",
                "searchdb [cat] [attr] [query]",
                "   Searches the database",
                "   [cat]: The type of object you're searching for (artist, song, release).",
                "   [attr]: The attribute you're searching by (name, type, title, duration, rating, artistName, guid, dateRange, trackGUID, trackName, artistGUID).",
                "   [query]: Your search query.",
                "add [guid] <date>",
                "   Adds to your personal library.",
                "   [guid]: The GUID of a song or release you want to add.",
                "   <date>: (OPTIONAL) The date the song or release was acquired. Defaults to the current time. Format in MM-DD-YYYY",
                "remove [guid]",
                "   Removes from your personal library.",
                "   [guid]: The GUID of a song or release you want to remove.",
                "rate [guid] [rating]",
                "   Rates a song",
                "   [guid]: The GUID of the song you want to rate",
                "   [rating]: The rating you want to give the song. An integer between 1 and 5.",
                "browse",
                "   Enters into browsing mode. Displays your personal library.",
                "quit",
                "   Exits the program.",
        };

        for (String line : message) {
            commandLineInterface.outputMessage(line);
        }
    }

    @Override
    protected void handleInput(String input) {
        String[] args = input.split(" ");
        String command = args[0];
        CommandLineInterface commandLineInterface = this.getCommandLineInterface();

        if (command.equals("searchlib")) {
            if (args.length < 4) {
                this.unknownCommand();
            } else {
                String cat = args[1].toLowerCase();
                String attr = args[2].toLowerCase();
                String query = "";

                for (int i = 3; i < args.length; i++) {
                    query += args[i];

                    if (i < args.length - 1) {
                        query += " ";
                    }
                }

                if (cat.equals("artist")) {
                    boolean correctAttr = true;

                    if (attr.equals("name")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchArtistByName());
                    } else if (attr.equals("rating")) {
                        commandLineInterface.setLibSearchStrategy(new SearchArtistByRating());
                    } else if (attr.equals("type")) {
                        commandLineInterface.setLibSearchStrategy(new SearchArtistByType());
                    } else {
                        correctAttr = false;
                    }

                    if (correctAttr) {
                        LinkedList<Artist> results = commandLineInterface.searchLibrary(query);
                        printArtistResults(results);
                    } else {
                        commandLineInterface.outputMessage("Error: Songs can only be searched by name, rating, or type");
                    }
                } else if (cat.equals("release")) {
                    boolean correctAttr = true;

                    if (attr.equals("artistguid")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchReleaseByArtistGUID());
                    } else if (attr.equals("artistname")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchReleasebyArtistName());
                    } else if (attr.equals("maxduration")) {
                        commandLineInterface.setLibSearchStrategy(new SearchReleaseByMaxDuration());
                    } else if (attr.equals("minduration")) {
                        commandLineInterface.setLibSearchStrategy(new SearchReleaseByMinDuration());
                    } else if (attr.equals("rating")) {
                        commandLineInterface.setLibSearchStrategy(new SearchReleaseByRating());
                    } else if (attr.equals("title")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchReleaseByTitle());
                    } else if (attr.equals("trackguid")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchReleaseByTrackGUID());
                    } else if (attr.equals("trackname")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchReleaseByTrackName());
                    } else {
                        correctAttr = false;
                    }

                    if (correctAttr) {
                        LinkedList<Release> results = commandLineInterface.searchLibrary(query);
                        printReleaseResults(results);
                    } else {
                        commandLineInterface.outputMessage("Error: Releases can only be searched by artistGUID, artistName, maxDuration, minDuration, rating, title, trackGUID, or trackName");
                    }
                } else if (cat.equals("song")) {
                    boolean correctAttr = true;

                    if (attr.equals("artistguid")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByArtistGUID());
                    } else if (attr.equals("artistname")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByArtistName());
                    } else if (attr.equals("maxduration")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByMaxDuration());
                    } else if (attr.equals("minduration")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByMinDuration());
                    } else if (attr.equals("rating")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByRating());
                    } else if (attr.equals("releaseguid")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByReleaseGUID());
                    } else if (attr.equals("releasetitle")) {
                        commandLineInterface.setLibSearchStrategy(new SearchSongByReleaseTitle());
                    } else if (attr.equals("title")) {
                        commandLineInterface.setLibSearchStrategy(new com.swen262.librarySearches.SearchSongByTitle());
                    } else {
                        correctAttr = false;
                    }

                    if (correctAttr) {
                        LinkedList<Song> results = commandLineInterface.searchLibrary(query);
                        printSongResults(results);
                    } else {
                        commandLineInterface.outputMessage("Error: Songs can only be searched by artistGUID, artistName, maxDuration, minDuration, rating, releaseGUID, releaseTitle, or title");
                    }
                } else {
                    commandLineInterface.outputMessage("Error: Only artists, releases and songs can be searched for.");
                }
            }
        } else if (command.equals("searchdb")) {
            if (args.length < 4) {
                this.unknownCommand();
            } else {
                String cat = args[1].toLowerCase();
                String attr = args[2].toLowerCase();
                String query = "";

                for (int i = 3; i < args.length; i++) {
                    query += args[i];

                    if (i < args.length - 1) {
                        query += " ";
                    }
                }

                if (cat.equals("artist")) {
                    if (attr.equals("name")) {
                        commandLineInterface.setDBSearchStrategy(new SearchArtistByName());
                        LinkedList<Artist> results = commandLineInterface.searchDatabase(query);
                        printArtistResults(results);
                    } else {
                        commandLineInterface.outputMessage("Error: Artists can only be searched by name.");
                    }
                } else if (cat.equals("release")) {
                    boolean correctAttr = true;

                    if (attr.equals("artistguid")) {
                        commandLineInterface.setDBSearchStrategy(new SearchReleaseByArtistGUID());
                    } else if (attr.equals("artistname")) {
                        commandLineInterface.setDBSearchStrategy(new SearchReleaseByArtistName());
                    } else if (attr.equals("daterange")) {
                        commandLineInterface.setDBSearchStrategy(new SearchReleaseByDateRange());
                    } else if (attr.equals("title")) {
                        commandLineInterface.setDBSearchStrategy(new SearchReleaseByTitle());
                    } else if (attr.equals("trackguid")) {
                        commandLineInterface.setDBSearchStrategy(new SearchReleaseByTrackGUID());
                    } else if (attr.equals("trackname")) {
                        commandLineInterface.setDBSearchStrategy(new SearchReleaseByTrackName());
                    } else {
                        correctAttr = false;
                    }

                    if (correctAttr) {
                        LinkedList<Release> results = commandLineInterface.searchDatabase(query);
                        printReleaseResults(results);
                    } else {
                        commandLineInterface.outputMessage("Error: Releases can only be searched by artistGUID, artistName, dateRange, title, trackGUID, or trackName");
                    }
                } else if (cat.equals("song")) {
                    boolean correctAttr = true;

                    if (attr.equals("artist")) {
                        commandLineInterface.setDBSearchStrategy(new SearchSongByArtist());
                    } else if (attr.equals("duration")) {
                        commandLineInterface.setDBSearchStrategy(new SearchSongByDuration());
                    } else if (attr.equals("title")) {
                        commandLineInterface.setDBSearchStrategy(new SearchSongByTitle());
                    } else {
                        correctAttr = false;
                    }

                    if (correctAttr) {
                        LinkedList<Song> results = commandLineInterface.searchDatabase(query);
                        printSongResults(results);
                    } else {
                        commandLineInterface.outputMessage("Error: Songs can only be searched by artist, duration, or title");
                    }
                } else {
                    commandLineInterface.outputMessage("Error: Only artists, releases and songs can be searched for.");
                }
            }
        } else if (command.equals("add")) {
            // TODO: ADD DATE
            if (args.length < 2) {
                this.unknownCommand();
            } else {
                try {
                    String GUID = args[1];
                    LocalDate date;

                    if (args.length >= 3) {
                        String dateStr = args[2];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
                        date = LocalDate.parse(dateStr, formatter);
                    } else {
                        date = LocalDate.now();
                    }

                    commandLineInterface.addByGUID(GUID, date);

                    int numSongs = library.getSongCount();
                    int numReleases = library.getReleaseCount();
                    commandLineInterface.outputMessage("Successfully added. Library now has " + numSongs + " songs and " + numReleases + " releases.");
                } catch (GUIDNotFoundException e) {
                    commandLineInterface.outputMessage("Error adding to library. Could not find song or release with matching GUID.");
                } catch (Exception e) {
                    commandLineInterface.outputMessage("Something went wrong.");
                }
            }
        } else if (command.equals("remove")) {
            try {
                String GUID = args[1];
                commandLineInterface.removeByGUID(GUID);

                int numSongs = library.getSongCount();
                int numReleases = library.getReleaseCount();
                commandLineInterface.outputMessage("Successfully removed. Library now has " + numSongs + " songs and " + numReleases + " releases.");
            } catch (Exception e) {
                commandLineInterface.outputMessage("Something went wrong.");
            }
        } else if (command.equals("rate")) {
            try {
                if (args.length < 3) {
                    this.unknownCommand();
                } else {
                    String GUID = args[1];
                    int rating = Integer.parseInt(args[2]);

                    if (rating > 5 || rating < 1) {
                        commandLineInterface.outputMessage("Ratings must be between 1 and 5.");
                    } else {
                        commandLineInterface.rateByGUID(GUID, rating);
                    }
                }
            } catch (GUIDNotFoundException e) {
                commandLineInterface.outputMessage("Error rating song. Could not find song with matching GUID.");
            } catch (Exception e) {
                commandLineInterface.outputMessage("Something went wrong.");
            }
        } else if (command.equals("browse")) {
            commandLineInterface.setMode(new ChoosingArtist(commandLineInterface));
        } else if (command.equals("quit")) {
            commandLineInterface.quit();
        } else if (command.equals("help")) {
            listCommands();
        } else {
            this.unknownCommand();
        }
    }
}
