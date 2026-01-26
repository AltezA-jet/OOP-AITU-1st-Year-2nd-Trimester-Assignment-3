package repository;

import model.Artist;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {

    public void insert(Artist artist) throws DatabaseOperationException {
        String sql = "INSERT INTO artists (name, country) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getCountry());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error inserting artist", e);
        }
    }

    public List<Artist> getAll() throws DatabaseOperationException {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artists";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country")
                );
                artists.add(artist);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching artists", e);
        }
        return artists;
    }

    public void delete(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM artists WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting artist", e);
        }
    }
}
