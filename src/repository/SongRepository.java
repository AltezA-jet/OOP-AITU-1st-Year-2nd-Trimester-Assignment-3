package repository;

import model.SongBase;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    public void insert(SongBase song) throws DatabaseOperationException {
        String sql = "INSERT INTO songs (name, artist, album, genre, duration, release_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, song.getName());
            ps.setString(2, song.getArtist());
            ps.setString(3, song.getAlbum());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getDuration());
            ps.setDate(6, Date.valueOf(song.getReleaseDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error inserting song", e);
        }
    }

    public List<SongBase> getAll() throws DatabaseOperationException {
        List<SongBase> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SongBase song = new model.MetalSong(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("artist"),
                        rs.getString("album"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getString("release_date"),
                        true, 120, true
                );
                songs.add(song);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching songs", e);
        }
        return songs;
    }

    public void delete(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM songs WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting song", e);
        }
    }

    public void update(int id, SongBase song) throws DatabaseOperationException {
        String sql = "UPDATE songs SET name=?, artist=?, album=?, genre=?, duration=?, release_date=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, song.getName());
            ps.setString(2, song.getArtist());
            ps.setString(3, song.getAlbum());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getDuration());
            ps.setDate(6, Date.valueOf(song.getReleaseDate()));
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating song", e);
        }
    }
}
