package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    
    public void addSong(SongBase song) {
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
            System.out.println("Added: " + song.getName());
        } catch (SQLException e) {
            System.out.println("Error adding song: " + e.getMessage());
        }
    }

    
    public List<String> getAllSongs() {
        List<String> songs = new ArrayList<>();
        String sql = "SELECT id, name, artist, genre, duration FROM songs ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String song = rs.getInt("id") + ". "
                        + rs.getString("name") + " by " + rs.getString("artist")
                        + " (" + rs.getString("genre") + ", " + rs.getInt("duration") + "s)";
                songs.add(song);
            }

        } catch (SQLException e) {
            System.out.println("Error reading songs: " + e.getMessage());
        }
        return songs;
    }

    
    public String getSongById(int id) {
        String sql = "SELECT * FROM songs WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id") + ". " + rs.getString("name")
                        + " by " + rs.getString("artist") + " (" + rs.getString("genre") + ")";
            } else {
                return "Song not found (id=" + id + ")";
            }
        } catch (SQLException e) {
            return "    Error fetching song: " + e.getMessage();
        }
    }

    public void updateSong(int id, String newName, int newDuration) {
        String sql = "UPDATE songs SET name = ?, duration = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, newDuration);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated song ID " + id + " to: " + newName + " (" + newDuration + "s)");
            } else {
                System.out.println("Song not found (id=" + id + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error updating song: " + e.getMessage());
        }
    }

    
    public void deleteSong(int id) {
        String sql = "DELETE FROM songs WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Deleted song (id=" + id + ")");
            else
                System.out.println("Song not found (id=" + id + ")");
        } catch (SQLException e) {
            System.out.println("Error deleting song: " + e.getMessage());
        }
    }
}
