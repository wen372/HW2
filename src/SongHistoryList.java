public class SongHistoryList {
    private Song top;
    public SongHistoryList(){
        // constructor for creating a new list
    }
    public boolean isEmpty(){
        return top == null;
    }
    public void addSong(Song song){
        if(isEmpty()){
            top = song;
        }
        else{
            song.setNext(top);
            top = song;
        }
    }
    public Song lastListened(){
        // retrieves the next song to listen to
        return top;
    }
}