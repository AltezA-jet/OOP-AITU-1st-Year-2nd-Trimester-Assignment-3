package repository;

import model.Album;
import utils.DatabaseConnection;
import exception.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {

    public void addAlbum(Album album) throws DatabaseOperationException {
        String sql = "INSERT INTO albums (title, release_year) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getReleaseYear());
            ps.executeUpdate();
            System.out.println("Album added: " + album.getTitle());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error adding album", e);
        }
    }

    public List<String> getAllAlbums() throws DatabaseOperationException {
        List<String> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                albums.add(rs.getInt("id") + ". " + rs.getString("title") + " (" + rs.getInt("release_year") + ")");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching albums", e);
        }
        return albums;
    }
}
