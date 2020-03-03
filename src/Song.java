public class Song {
    private String track;
    private Song next;
    private Song last;
    // add constructors
    public Song(String name){
        track = name;
    }

    public Song getNext(){
        return next;
    }
    public void setNext(Song song){
        next = song;
    }

    public Song getLast(){
        return last;
    }

    public void setLast(Song song){
        last = song;
    }
    public String toString(){
        return track;
    }



}
