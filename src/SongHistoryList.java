public class SongHistoryList {
    private Song first;
    public void SongHistoryList(){
        // constructor for creating a new list
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void addSong(Song song){
        if(isEmpty()){
            first = song;
        }
        else{
            song.setNext(first);
            first = song;
        }
    }
    public Song lastListened(){
        // retrieves the next song to listen to
        return first;
    }
}