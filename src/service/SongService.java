package service;

import model.SongBase;
import repository.SongRepository;
import exception.*;
import java.sql.*;
import utils.DatabaseConnection;
import java.util.List;

public class SongService {

    private final SongRepository repo = new SongRepository();

    public void createSong(SongBase song) throws InvalidInputException, DuplicateResourceException, DatabaseOperationException {
        if (song == null || !song.isValid()) throw new InvalidInputException("Invalid song data");

        if (existsByName(song.getName(), song.getArtist()))
            throw new DuplicateResourceException("Song already exists");

        repo.insert(song);
    }

    public List<SongBase> getAllSongs() throws DatabaseOperationException {
        return repo.getAll();
    }

    public void updateSong(int id, SongBase song) throws InvalidInputException, DatabaseOperationException {
        if (song == null || !song.isValid()) throw new InvalidInputException("Invalid song data");
        repo.update(id, song);
    }

    public void deleteSong(int id) throws DatabaseOperationException {
        repo.delete(id);
    }

    private boolean existsByName(String name, String artist) throws DatabaseOperationException {
        String sql = "SELECT COUNT(*) FROM songs WHERE name = ? AND artist = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, artist);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error checking duplicates", e);
        }
    }
    
}
