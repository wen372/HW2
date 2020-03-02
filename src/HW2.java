

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class HW2 {


    public static void main(String[] args) {
        ArrayList<String> fileNames = readFileNames();
        int numberOfCSV = countCSV(fileNames);
        System.out.println(numberOfCSV + " Files successfully loaded");

        //creates MyQueue array with 12 spots(for a year quarter)
        MyQueue[] allWeeks = new MyQueue[12];
        Iterator files = fileNames.iterator();
        int count = 0;

        //fills MyQueue array with objects containing song list data
        while(files.hasNext() && count < 12){
            allWeeks[count] = new MyQueue(files.next().toString());
            count++;
        }


        //merges all the MyQueue objects into one
        MyQueue combined = MyQueue.mergingFunction(allWeeks[0], allWeeks[1]);
        for(int i=2; i<12; i++) {
            combined = MyQueue.mergingFunction(combined, allWeeks[i]);
        }

        //creates playlist and adds combined songlist into playlist
        Playlist playlist = new Playlist();
        while(!(combined.isEmpty())){
            playlist.addSong(new Song(combined.poll().toString()));
        }

        /*
        System.out.println(playlist.listenToSong());
        System.out.println(playlist.listenToSong());
        System.out.println(playlist.listenToSong());
        System.out.println(playlist.listenToSong());
        System.out.println(playlist.lastListened());
        System.out.println(playlist.lastListened());
        System.out.println(playlist.listenToSong());
        System.out.println(playlist.lastListened());
        System.out.println(playlist.lastListened());
        System.out.println(playlist.listenToSong());
        System.out.println(playlist.lastListened());
        System.out.println(playlist.lastListened());
         */


        while(!(playlist.isEmpty())){
            System.out.println(playlist.listenToSong());
        }



    }

    //reads in file names from data directory and returns arraylist with names
    public static ArrayList<String> readFileNames(){
        ArrayList<String> results = new ArrayList<>();
        File[] folder = new File("../data/").listFiles();
        if(folder != null) {
            for (File files : folder) {
                if (files.isFile()) {
                    results.add(files.getName());
                }
            }
        }else{
            System.out.println("No data folder found");
        }
        if(folder != null && folder.length == 0){
            System.out.println("No Files in folder. Add .csv files into data folder");
        }
        return results;
    }

    //parses through array and removes non csv file names returns count of csv files
    public static int countCSV(ArrayList<String> fileNames){
        //Arraylist holds names that need to be removed (added because of compiler issues)
        ArrayList<String> removeNames = new ArrayList<>();
        int count = 0;

        for (String names : fileNames){
            //checks if names are csv files
            String extention = names.substring(names.length()-3,names.length());
            if(extention.equals("csv")) {
                count++;
            }
            else {
                removeNames.add(names);
            }
        }
        fileNames.removeAll(removeNames);
        return count;
    }
}

