package model;

// import model.Validatable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Validatable {
    private int id;
    private String title;
    private int releaseYear;
    private List<SongBase> songs;

    public Album(int id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.songs = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }

    public void addSong(SongBase song) {
        if (song != null) songs.add(song);
    }

    public List<SongBase> getSongs() { return songs; }

    @Override
    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && releaseYear > 1900;
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + releaseYear + ")";
    }
}
