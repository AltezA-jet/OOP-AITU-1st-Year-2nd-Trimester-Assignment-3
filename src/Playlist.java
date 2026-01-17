import java.util.ArrayList;
import java.util.List;
public class Playlist {
    private int id;
    private String name;
    private List<SongBase> songs;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
    }
    public void addSong(SongBase song) {
        if (songs.contains(song)) {
            System.out.println(" This song is already in the playlist.");
            return;
        }
        songs.add(song);
        System.out.println(" Added: " + song.getName());
    }
    public void removeSong(SongBase song) {
        songs.remove(song);
        System.out.println("Removed: " + song.getName());
    }

    public void showPlaylist() {
        System.out.println("\n Playlist: " + name);
        if (songs.isEmpty()) {
            System.out.println("No songs in this playlist yet.");
        } else {
            for (SongBase song : songs) {
                song.showInfo();
            }
        }
    }

    
    public String getName() {
        return name;
    }

    public List<SongBase> getSongs() {
        return songs;
    }

}
