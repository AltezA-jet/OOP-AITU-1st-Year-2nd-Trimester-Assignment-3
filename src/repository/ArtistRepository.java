package repository;

import model.Artist;
import utils.DatabaseConnection;
import exception.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {

    public void addArtist(Artist artist) throws DatabaseOperationException {
        String sql = "INSERT INTO artists (name, country) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getCountry());
            ps.executeUpdate();
            System.out.println("Artist added: " + artist.getName());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error adding artist", e);
        }
    }

    public List<String> getAllArtists() throws DatabaseOperationException {
        List<String> artists = new ArrayList<>();
        String sql = "SELECT * FROM artists ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                artists.add(rs.getInt("id") + ". " + rs.getString("name") + " (" + rs.getString("country") + ")");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching artists", e);
        }
        return artists;
    }
}
