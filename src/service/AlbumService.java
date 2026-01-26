package service;

import model.Album;
import repository.AlbumRepository;
import exception.*;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class AlbumService {

    private final AlbumRepository repo = new AlbumRepository();

    public void createAlbum(Album album) throws InvalidInputException, DuplicateResourceException, DatabaseOperationException {
        if (album == null || !album.isValid()) throw new InvalidInputException("Invalid album data");
        if (existsByTitle(album.getTitle())) throw new DuplicateResourceException("Album already exists");
        repo.insert(album);
    }

    public List<Album> getAllAlbums() throws DatabaseOperationException {
        return repo.getAll();
    }

    public void deleteAlbum(int id) throws DatabaseOperationException {
        repo.delete(id);
    }

    private boolean existsByTitle(String title) throws DatabaseOperationException {
        String sql = "SELECT COUNT(*) FROM albums WHERE title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error checking album duplicates", e);
        }
    }
}
    