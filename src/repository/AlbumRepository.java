package repository;

import model.Album;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {

    public void insert(Album album) throws DatabaseOperationException {
        String sql = "INSERT INTO albums (title, release_year) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getReleaseYear());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error inserting album", e);
        }
    }

    public List<Album> getAll() throws DatabaseOperationException {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Album album = new Album(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("release_year")
                );
                albums.add(album);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching albums", e);
        }
        return albums;
    }

    public void delete(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM albums WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting album", e);
        }
    }
}
