package controller;

import model.*;
import service.*;
import exception.*;

// import java.util.List;

public class Main {
    public static void main(String[] args) {
        SongService songService = new SongService();
        PlaylistService playlistService = new PlaylistService();
        ArtistService artistService = new ArtistService();
        AlbumService albumService = new AlbumService();

        System.out.println("=== Starting Data Operations ===");

 
        System.out.println("\n--- Creating Artists ---");
        try {
            artistService.createArtist(new Artist(11, "Metallica new era", "USA"));
            System.out.println("Artist Metallica created.");
        } catch (Exception e) { System.out.println(" Artist 11 skipped: " + e.getMessage()); }
        try {
            artistService.createArtist(new Artist(12, "Black Sabbath ", "UK"));
            System.out.println("Artist Black Sabbath created.");
        } catch (Exception e) { System.out.println(" Artist 12 skipped: " + e.getMessage()); }


        System.out.println("\n--- Creating Albums ---");
        try {
            albumService.createAlbum(new Album(21, "Master of Puppets 2.0", 1986));
            System.out.println("Album Master of Puppets created.");
        } catch (Exception e) { System.out.println(" Album 21 skipped: " + e.getMessage()); }
        try {
            albumService.createAlbum(new Album(22, "Paranoid 2.0", 1970));
            System.out.println("Album Paranoid created.");
        } catch (Exception e) { System.out.println(" Album 22 skipped: " + e.getMessage()); }


        System.out.println("\n--- Creating Songs ---");
        SongBase song1 = new MetalSong(1, "Master of Puppets remaster", "Metallica", "Master of Puppets", "Metal", 515, "1986-03-03", true, 220, true);
        SongBase song2 = new MetalSong(2, "War Pigs remastered", "Black Sabbath", "Paranoid", "Metal", 470, "1970-09-18", true, 180, true);
        try {
            songService.createSong(song1);
            System.out.println("Song 1 created.");
        } catch (Exception e) { System.out.println(" Song 1 skipped: " + e.getMessage()); }
        try {
            songService.createSong(song2);
            System.out.println("Song 2 created.");
        } catch (Exception e) { System.out.println(" Song 2 skipped: " + e.getMessage()); }


        System.out.println("\n--- Setting up Playlist ---");
        try {
            playlistService.createPlaylist(new Playlist(1, "Heavy Legends"));
            System.out.println("Playlist created.");
        } catch (Exception e) { System.out.println(" Playlist 1 skipped: " + e.getMessage()); }
        try {
            playlistService.addSongToPlaylist(1, 1);
            playlistService.addSongToPlaylist(1, 2);
            System.out.println("Songs added to playlist.");
        } catch (Exception e) { System.out.println(" Playlist linking skipped: " + e.getMessage()); }
        

        try {
            printArtistData(artistService);
            printAlbumData(albumService);
            printSongData(songService);
        } catch (DatabaseOperationException e) {
            System.out.println("Error displaying data: " + e.getMessage());
        }


        System.out.println("\n=== Actions ===");
        try {
            System.out.println("Deleting Song ID 2 (War Pigs)...");
            songService.deleteSong(2);
            System.out.println("Song deleted.");
        } catch (Exception e) {
            System.out.println("Error deleting song ID 2: " + e.getMessage());
        }

        try {
            printSongData(songService);
        } catch (DatabaseOperationException e) {
            System.out.println("Error displaying final song data: " + e.getMessage());
        }
        
        System.out.println("\n=== Program Finished ===");
    }

 
    private static void printArtistData(ArtistService service) throws DatabaseOperationException {
        System.out.println("\n=== ARTISTS DATA ===");
        System.out.println("ID | Name | Country");
        System.out.println("---------------------");
        for (Artist a : service.getAllArtists()) {
            System.out.println(a.getId() + " | " + a.getName() + " | " + a.getCountry());
        }
    }

    private static void printAlbumData(AlbumService service) throws DatabaseOperationException {
        System.out.println("\n=== ALBUMS DATA ===");
        System.out.println("ID | Title | Year");
        System.out.println("---------------------");
        for (Album al : service.getAllAlbums()) {
             System.out.println(al.getId() + " | " + al.getTitle() + " | " + al.getReleaseYear());
        }
    }

    private static void printSongData(SongService service) throws DatabaseOperationException {
        System.out.println("\n=== SONGS DATA ===");
        System.out.println("ID | Title | Artist | Genre");
        System.out.println("---------------------");
        for (SongBase s : service.getAllSongs()) {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getArtist() + " | " + s.getGenre());
        }
    }
}
