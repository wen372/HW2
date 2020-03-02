

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/* Storing one week of data in a queue */
public class MyQueue extends LinkedList {

    // add a no param constructor
    public MyQueue (){

    }

    // constructor creates a linked list that stores songs from one text file
    public MyQueue(String filename) {
        //arraylist to temporarily hold names of songs
        ArrayList<String> arr = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File("../data/" + filename));
            //skips header of line
            file.nextLine();
            file.nextLine();
            //goings through each row of file
            while(file.hasNextLine()){
                String line = file.nextLine();
                int comma = line.indexOf(",");
                int commaAfterName = line.indexOf(",",comma+1);
                boolean correctCommaPlace = false;
                //separates commas surrounding song title while maintaining commas in title name
                while (!correctCommaPlace) {
                    if (line.charAt(commaAfterName + 1) == ' ' || (Character.isDigit(line.charAt(commaAfterName - 1)) && Character.isDigit(line.charAt(commaAfterName + 1)) && Character.isDigit(line.charAt(commaAfterName + 2)))) {
                            commaAfterName = line.indexOf(",", commaAfterName + 1);
                    } else {
                        correctCommaPlace = true;
                    }
                }
                String songName = line.substring(comma+1,commaAfterName);
                //removes quotations from song name
                if(songName.charAt(0) == '"')
                    songName = songName.substring(1,songName.length()-1);

                arr.add(songName);
            }
            arr.sort(String.CASE_INSENSITIVE_ORDER);
            for(String names : arr){
                add(names);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    /* This function takes two sorted queues and returns one sorted queue */
    public static MyQueue mergingFunction(MyQueue q1, MyQueue q2){

        //queue that holds combined names
        MyQueue queue = new MyQueue();
        //checks names of both sides of the queue at the front
        while(!(q1.isEmpty()) && !(q2.isEmpty())){
                int compare = q1.peek().toString().compareToIgnoreCase(q2.peek().toString());
                //adds element from q1 into queue
                if (compare < 0) {
                    queue.add(q1.poll());
                }
                //adds element q1 to queue and deletes element at q2 head (equals)
                if (compare == 0) {
                    queue.add(q1.poll());
                    q2.remove();
                }
                //adds element from q2 into queue
                if (compare > 0) {
                    queue.add(q2.poll());
            }

        }
        //if q1 is empty adds rest of q2
        if(q1.isEmpty()){
            while(!(q2.isEmpty())){
                queue.add(q2.poll());
            }
        //if q2 is empty adds rest of q1
        }else if(q2.isEmpty()){
            while(!(q1.isEmpty())){
                queue.add(q1.poll());
            }
        }
        return queue;

    }
}




