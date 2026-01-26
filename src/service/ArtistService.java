package service;

import model.Artist;
import repository.ArtistRepository;
import exception.*;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class ArtistService {

    private final ArtistRepository repo = new ArtistRepository();

    public void createArtist(Artist artist) throws DatabaseOperationException {
    if (!existsByName(artist.getName())) {  
        repo.insert(artist);
        
    } else {
        System.out.println("Artist already exists: " + artist.getName());
    }
}


    public List<Artist> getAllArtists() throws DatabaseOperationException {
        return repo.getAll();
    }

    public void deleteArtist(int id) throws DatabaseOperationException {
        repo.delete(id);
    }

    private boolean existsByName(String name) throws DatabaseOperationException {
        String sql = "SELECT COUNT(*) FROM artists WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error checking artist duplicates", e);
        }
    }
}
