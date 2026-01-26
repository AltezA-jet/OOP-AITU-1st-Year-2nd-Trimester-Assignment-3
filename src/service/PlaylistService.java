package service;

import model.Playlist;
import repository.PlaylistRepository;
import exception.*;
import java.util.List;

public class PlaylistService {

    private final PlaylistRepository playlistRepo = new PlaylistRepository();

    
    public void createPlaylist(Playlist playlist) throws InvalidInputException, DatabaseOperationException {
        if (!playlist.isValid()) {
            throw new InvalidInputException("Invalid playlist data");
        }
        playlistRepo.createPlaylist(playlist);
    }

    
    public List<String> getAllPlaylists() throws DatabaseOperationException {
        return playlistRepo.getAllPlaylists();
    }

    
    public void addSongToPlaylist(int playlistId, int songId) throws DatabaseOperationException {
        playlistRepo.addSongToPlaylist(playlistId, songId);
    }

    
    public List<String> getSongsFromPlaylist(int playlistId) throws DatabaseOperationException {
        return playlistRepo.getSongsFromPlaylist(playlistId);
    }

    
    public void deletePlaylist(int id) throws DatabaseOperationException {
        playlistRepo.deletePlaylist(id);
    }
}
