package com.swen262;

import org.apache.commons.cli.*;

import java.util.Date;
import java.util.LinkedHashSet;

public class Main {

    private static Database db;
    private static String song_path;
    private static String release_path;
    private static String artist_path;


    public static void main(String[] args) {
        commandLineParser(args);
        db = new Database(song_path,release_path,artist_path);
	// write your code here
    }

    private static void helpQuit(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mmls",options);
        System.exit(0);
    }

    private static void commandLineParser(String[] args){
        Options options = new Options();
        options.addOption("h",false,"see help message");
        options.addOption("artists",true,"(Required) comma-delimited file of artists and their attributes");
        options.addOption("releases",true,"(Required) comma-delimited file of releases and their attributes");
        options.addOption("songs",true,"(Required) comma-delimited file of songs and their attributes");

        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine line = parser.parse(options,args);
            if (line.hasOption("h")){
                helpQuit(options);
            }
            if (line.hasOption("artists") && line.hasOption("releases") && line.hasOption("songs")){
                artist_path = line.getOptionValue("artists");
                release_path = line.getOptionValue("releases");
                song_path = line.getOptionValue("songs");
                System.out.println(artist_path);
            } else {helpQuit(options);}

        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
