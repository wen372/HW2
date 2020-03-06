import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class HW2 {


    public static void main(String[] args) {

        ArrayList<String> fileNames = readFileNames();
        int numberOfCSV = countCSV(fileNames);
        System.out.println(numberOfCSV + " Files successfully loaded");

        //creates MyQueue array with 12 spots(for a year quarter( 12 weeks)
        MyQueue[] allWeeks = new MyQueue[12];
        Iterator files = fileNames.iterator();
        int count = 0;

        //fills MyQueue array with objects containing song list data
        while(files.hasNext() && count < 12){
            allWeeks[count] = new MyQueue(files.next().toString());
            count++;
        }


        //merges all the MyQueue objects "weeks" into one
        MyQueue combined = new MyQueue();
        for(int i=0; i<count; i++) {
            combined = MyQueue.mergingFunction(combined, allWeeks[i]);
        }

        //creates playlist and adds combined songlist into playlist
        Playlist playlist = new Playlist();
        while(!(combined.isEmpty())){
            playlist.addSong(new Song(combined.poll().toString()));
        }

        System.out.println();
        System.out.println("Listening to PlayList");

        Scanner in = new Scanner(System.in);
        String input;
        do{
            System.out.println("Press 1 to play next song \nPress 2 to get name last song played \nPress q to quit");
            input = in.next();
            switch(input){
                case "1":
                    System.out.println("Now Playing: " + playlist.listenToSong());
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Last Song: " + playlist.lastListened());
                    System.out.println();
                    break;
            }

        }while(!(input.equals("q")));


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
