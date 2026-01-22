package controller;
public class Main {
    public static void main(String[] args) {
        SongRepository repo = new SongRepository();

        System.out.println("🎵 Songs in DB:");
        for (String s : repo.getAllSongs()) {
            System.out.println(" - " + s);
        }

        MetalSong newSong = new MetalSong(
            99, "New Metal Anthem", "Metallica", "Reload",
            "Metal", 350, "1997-05-15", true, 140, true
        );
        repo.addSong(newSong);

        repo.updateSong(99, "New Metal Anthem (Remix)", 400);
        repo.deleteSong(99);
    }
}
