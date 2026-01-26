package service;

import model.SongBase;
import repository.SongRepository;
import exception.*;

import java.util.List;

public class SongService {

    private final SongRepository songRepo = new SongRepository();

    
    public void addSong(SongBase song) throws InvalidInputException, DatabaseOperationException {
        if (!song.isValid()) {
            throw new InvalidInputException("Invalid song data");
        }
        songRepo.addSong(song);
    }

    
    public List<String> getAllSongs() throws DatabaseOperationException {
        return songRepo.getAllSongs();
    }

    
    public void updateSong(int id, String newName, int newDuration) throws InvalidInputException, DatabaseOperationException {
        if (newName == null || newName.trim().isEmpty() || newDuration <= 0) {
            throw new InvalidInputException("Invalid input for update");
        }
        songRepo.updateSong(id, newName, newDuration);
    }

    
    public void deleteSong(int id) throws DatabaseOperationException {
        songRepo.deleteSong(id);
    }
}
