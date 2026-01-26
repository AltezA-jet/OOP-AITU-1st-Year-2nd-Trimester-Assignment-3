package repository;

import model.Playlist;
import utils.DatabaseConnection;
import exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {

    // CREATE
    public void createPlaylist(Playlist playlist) throws DatabaseOperationException {
        String sql = "INSERT INTO playlists (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playlist.getName());
            ps.executeUpdate();
            System.out.println("Created playlist: " + playlist.getName());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating playlist", e);
        }
    }

    // READ ALL
    public List<String> getAllPlaylists() throws DatabaseOperationException {
        List<String> playlists = new ArrayList<>();
        String sql = "SELECT id, name FROM playlists ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                playlists.add(rs.getInt("id") + ". " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error reading playlists", e);
        }
        return playlists;
    }

    // ADD SONG TO PLAYLIST
    public void addSongToPlaylist(int playlistId, int songId) throws DatabaseOperationException {
        String sql = "INSERT INTO playlist_songs (playlist_id, song_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();
            System.out.println("Added song " + songId + " to playlist " + playlistId);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error adding song to playlist", e);
        }
    }

    // GET SONGS FROM PLAYLIST
    public List<String> getSongsFromPlaylist(int playlistId) throws DatabaseOperationException {
        List<String> songs = new ArrayList<>();
        String sql = """
                SELECT s.id, s.name, s.artist, s.genre
                FROM songs s
                JOIN playlist_songs ps ON s.id = ps.song_id
                WHERE ps.playlist_id = ?
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                songs.add(rs.getInt("id") + ". "
                        + rs.getString("name") + " by " + rs.getString("artist")
                        + " (" + rs.getString("genre") + ")");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching songs from playlist", e);
        }
        return songs;
    }

    // DELETE PLAYLIST
    public void deletePlaylist(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Deleted playlist (id=" + id + ")");
            else
                System.out.println("Playlist not found (id=" + id + ")");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting playlist", e);
        }
    }
}
