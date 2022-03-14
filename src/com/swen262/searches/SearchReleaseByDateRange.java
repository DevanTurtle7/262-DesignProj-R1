package com.swen262.searches;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.swen262.Database;
import com.swen262.Release;

public class SearchReleaseByDateRange implements SongSearcher<Release>{

    @Override
    public LinkedList<Release> algorithm(String query, Database db) {
        LinkedList<Release> returnReleases = new LinkedList<>();
        String[] dates = query.split("-");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try{
            try{
                Date beginDate = formatter.parse(dates[0]);
                Date endDate = formatter.parse(dates[1]);
                for(Release release : db.getReleases()){
                    if(release.getIssueDate().before(endDate) && release.getIssueDate().after(beginDate)){
                        returnReleases.add(release);
                    }
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Ensure your date range has two valid dates of form mm/dd/yyyy seperated by a \"-\"");
            }
        }
        catch(ParseException PE){
            PE.printStackTrace();
        }
        return returnReleases;
    }
    
}
