package repository;

import model.Playlist;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {

    public void insert(Playlist playlist) throws DatabaseOperationException {
        String sql = "INSERT INTO playlists (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, playlist.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error inserting playlist", e);
        }
    }

    public List<Playlist> getAll() throws DatabaseOperationException {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlists";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getInt("id"), rs.getString("name"));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching playlists", e);
        }
        return playlists;
    }

    public void delete(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting playlist", e);
        }
    }

    public void addSongToPlaylist(int playlistId, int songId) throws DatabaseOperationException {
        String sql = "INSERT INTO playlist_songs (playlist_id, song_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error linking song to playlist", e);
        }
    }
}
