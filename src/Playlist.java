//implementation of Queue
public class Playlist {
    private Song back;
    private Song front;
    private SongHistoryList history = new SongHistoryList();
    //no arg constructor
    public Playlist(){

    }

    //checks if Queue is empty
    public boolean isEmpty(){
        return back == null;
    }

    //adds a song into queue
    public void addSong(Song song){
        if(isEmpty()) {
            back = song;
            front = song;
        }
        else{
            song.setNext(back);
            back.setLast(song);
            back = song;
        }
    }

    //returns Song at front and removes element
    public Song listenToSong(){
        Song song;
        if(isEmpty()){
            return null;
        }else{
            song = front;
            remove();
        }
        history.addSong(song);
        return song;
    }

    //removes front(head) of queue
    public void remove(){
        if(!(isEmpty())) {
            if (front.getLast() != null)
                front = front.getLast();
            else {
                front = null;
                back = null;
            }
        }
    }

    //returns Song without removing element
    public Song peek(){
        return front;
    }

    public Song lastListened(){
        // retrieves the next song to listen to
        return history.lastListened();
    }
}
