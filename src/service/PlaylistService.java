package service;

import model.Playlist;
import repository.PlaylistRepository;
import exception.*;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class PlaylistService {

    private final PlaylistRepository repo = new PlaylistRepository();

    public void createPlaylist(Playlist playlist) throws InvalidInputException, DuplicateResourceException, DatabaseOperationException {
        if (playlist == null || !playlist.isValid()) throw new InvalidInputException("Invalid playlist data");
        if (existsByName(playlist.getName())) throw new DuplicateResourceException("Playlist exists");
        repo.insert(playlist);
    }

    public List<Playlist> getAllPlaylists() throws DatabaseOperationException {
        return repo.getAll();
    }

    public void addSongToPlaylist(int playlistId, int songId) throws InvalidInputException, DatabaseOperationException {
        if (isSongInPlaylist(playlistId, songId)) throw new InvalidInputException("Song already in playlist");
        repo.addSongToPlaylist(playlistId, songId);
    }

    public void deletePlaylist(int id) throws DatabaseOperationException {
        repo.delete(id);
    }

    private boolean existsByName(String name) throws DatabaseOperationException {
        String sql = "SELECT COUNT(*) FROM playlists WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error checking playlist duplicates", e);
        }
    }

    private boolean isSongInPlaylist(int playlistId, int songId) throws DatabaseOperationException {
        String sql = "SELECT COUNT(*) FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error checking song in playlist", e);
        }
    }
}
