package com.swen262.searches;

import java.util.LinkedList;

import com.swen262.database.Database;
import com.swen262.Song;

public class SearchSongByDuration implements SongSearcher<Song>{

    class InvalidInput extends Exception{
        public InvalidInput(String message){
            super(message);
        }
    }

    @Override
    public LinkedList<Song> algorithm(String query, Database db) {
        String[] tokens = query.split(" ");
        LinkedList<Song> returnSongs = new LinkedList<>();
        try{
            String shorterOrLonger = tokens[0].toLowerCase();
            int duration = Integer.parseInt(tokens[1]);
            if(shorterOrLonger.equals("s")){
                for(Song song : db.getSongs()){
                    if(song.getDuration() < duration){
                        returnSongs.add(song);
                    }
                }
            }
            else if(shorterOrLonger.equals("l")){
                for(Song song : db.getSongs()){
                    if(song.getDuration() > duration){
                        returnSongs.add(song);
                    }
                }
            }
            else{
                throw new InvalidInput("Query must begin with an L or S");
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Ensure your input is entered correctly, seperated by a space. Example: \"s 150000\" to search for songs shorter than 150000ms");
        }
        catch(InvalidInput IE){
            System.out.println(IE.getMessage());
        }
        return returnSongs;
    }
    
}