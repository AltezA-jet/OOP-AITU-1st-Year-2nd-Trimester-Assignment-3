public abstract class SongBase implements Playable, Validatable{
    private int id;
    private String name;
    private String artist;
    private String album;
    private String genre;
    private int duration;
    private String releaseDate;
    

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
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getArtist(){
        return artist;
    }
    public String getalbum(){
        return album;
    }
    public String getGenre(){
        return genre;
    }
    public int getDuration(){
        return duration;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Song name cannot be empty.");
        }
        this.name = name;
    }
    public void showInfo() {
        System.out.println("Song: " + name + " | Artist: " + artist + " | Album: " + album);
    }
}