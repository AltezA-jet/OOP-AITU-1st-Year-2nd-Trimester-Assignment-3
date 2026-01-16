public abstract class SongBase{
    private int id;
    private String name;
    private String artist;
    private String album;
    private String genre;
    private int duration;
    private String releaseDate;
    
}
public SongBase(int id, String name, String artist, String album, String genre, int duration,String releaseDate){
    this.id=id;
    this.name=name;
    this.artist=artist;
    this.album=album;
    this.genre=genre;
    this.duration=duration;
    this.releaseDate=releaseDate;
}
public abstract void MusicPlay();
public abstract String getGenreType();
public void showInfo() {
    System.out.println("Song: " + name + " | Artist: " + artist + " | Album: " + album);
}