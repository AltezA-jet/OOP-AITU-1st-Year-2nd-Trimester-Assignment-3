package service;

import model.Album;
import repository.AlbumRepository;
import exception.*;
import java.util.List;

public class AlbumService {

    private final AlbumRepository albumRepo = new AlbumRepository();

    public void addAlbum(Album album) throws InvalidInputException, DatabaseOperationException {
        if (!album.isValid()) {
            throw new InvalidInputException("Invalid album data");
        }
        albumRepo.addAlbum(album);
    }

    public List<String> getAllAlbums() throws DatabaseOperationException {
        return albumRepo.getAllAlbums();
    }
}
