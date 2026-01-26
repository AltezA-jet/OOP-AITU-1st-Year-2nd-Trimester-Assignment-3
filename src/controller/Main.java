package controller;

import repository.SongRepository;
import repository.PlaylistRepository;

import model.Playlist;



public class Main {
    public static void main(String[] args) {
        SongRepository songRepo = new SongRepository();
        PlaylistRepository playlistRepo = new PlaylistRepository();

        try {
            
            Playlist metal = new Playlist(0, "Metal Legends");
            playlistRepo.createPlaylist(metal);

            
            System.out.println("Playlists:");
            for (String p : playlistRepo.getAllPlaylists()) {
                System.out.println(" - " + p);
            }

            
            playlistRepo.addSongToPlaylist(1, 2); 

            
            System.out.println("Songs in playlist:");
            for (String s : playlistRepo.getSongsFromPlaylist(1)) {
                System.out.println(" - " + s);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
