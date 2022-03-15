package com.swen262.personalLibrary;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.swen262.model.Artist;
import com.swen262.model.Release;
import com.swen262.model.Song;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

public class SaveLibrary implements Action {
    @Override
    public void performAction(Object o) throws Exception {
        if (o instanceof PersonalLibrary) {
            PersonalLibrary library = (PersonalLibrary) o;
            RFC4180Parser rfcDoubleParse = new RFC4180ParserBuilder().withQuoteChar('\"').build();
            File artistCSV = new File("src/com/swen262/personalLibrary/data/artists.csv");
            File songCSV = new File("src/com/swen262/personalLibrary/data/songs.csv");
            File releaseCSV = new File("src/com/swen262/personalLibrary/data/releases.csv");
            LinkedList<Song> songs = library.getSongs();
            HashSet<Artist> artists = library.getArtists();
            LinkedList<Release> releases = library.getReleases();
            ICSVWriter songcsvwrite = new CSVWriterBuilder(new FileWriter(songCSV.getAbsolutePath())).withParser(rfcDoubleParse).build();
            for (Song song : songs) {
                // title,artist_guid,duration,rating,GUID
                artists.add(song.getArtist());
                String[] csvline = {song.getTitle(), song.getArtist().getGUID(), String.valueOf(song.getDuration()), String.valueOf(song.getRating()), song.getGUID()};
                songcsvwrite.writeNext(csvline);

                //System.out.println(csvline[0]);
            }
            songcsvwrite.close();

            for (Release release : releases) {
                //issuedate,title,artist,medium,GUID,tracks
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date iss_date = release.getIssueDate();
                String dateStr = formatter.format(iss_date);
                LinkedList<String> csvlist = new LinkedList<>(Arrays.asList(dateStr, release.getTitle(), release.getArtist().getGUID(), release.getMedium(), release.getGUID()));
                LinkedList<Song> tks = release.getTracks();
                ICSVWriter songcsvwriter = new CSVWriterBuilder(new FileWriter(songCSV.getAbsolutePath(), true)).withParser(rfcDoubleParse).build();
                for (Song track : tks) {
                    String[] csvline = {track.getTitle(), track.getArtist().getGUID(), String.valueOf(track.getDuration()), String.valueOf(track.getRating()), track.getGUID()};
                    artists.add(track.getArtist());
                    songcsvwriter.writeNext(csvline);
                    //System.out.println(csvline[0]);
                    csvlist.add(track.getGUID());
                }
                songcsvwriter.close();
                String[] csvline = csvlist.toArray(new String[0]);
                ICSVWriter csvwrite = new CSVWriterBuilder(new FileWriter(releaseCSV.getAbsolutePath())).withParser(rfcDoubleParse).build();
                artists.add(release.getArtist());
                csvwrite.writeNext(csvline);
                csvwrite.close();
            }
            ICSVWriter artcsvwrite = new CSVWriterBuilder(new FileWriter(artistCSV.getAbsolutePath())).withParser(rfcDoubleParse).build();
            for (Artist artist : artists) {
                //name,type,rating,guid
                String[] csvline = {artist.getName(), artist.getType(), String.valueOf(artist.getRating()), artist.getGUID()};
                artcsvwrite.writeNext(csvline);
            }
            artcsvwrite.close();
        }
    }
}
