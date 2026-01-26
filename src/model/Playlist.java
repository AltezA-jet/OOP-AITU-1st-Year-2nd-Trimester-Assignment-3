package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Validatable, Playable {
    private int id;
    private String name;
    private List<SongBase> songs;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<SongBase> getSongs() { return songs; }

    public void addSong(SongBase song) {
        if (song == null) throw new IllegalArgumentException("Cannot add null song");
        if (!songs.contains(song)) {
            songs.add(song);
            System.out.println("Added: " + song.getName());
        } else {
            System.out.println("Song already in playlist");
        }
    }

    public void removeSong(SongBase song) {
        songs.remove(song);
    }

    public void showPlaylist() {
        System.out.println("\nPlaylist: " + name);
        if (songs.isEmpty()) System.out.println("No songs yet");
        else for (SongBase s : songs) s.showInfo();
    }

    @Override
    public boolean isValid() {
        return name != null && !name.trim().isEmpty();
    }

    @Override
    public void play() {
        if (songs.isEmpty()) {
            System.out.println("No songs to play in playlist: " + name);
        } else {
            System.out.println(" Playing playlist: " + name);
            for (SongBase s : songs) s.play();
        }
    }
}
