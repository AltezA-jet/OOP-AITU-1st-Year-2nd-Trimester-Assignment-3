package service;

import model.Artist;
import repository.ArtistRepository;
import exception.*;
import java.util.List;

public class ArtistService {

    private final ArtistRepository artistRepo = new ArtistRepository();

    public void addArtist(Artist artist) throws InvalidInputException, DatabaseOperationException {
        if (!artist.isValid()) {
            throw new InvalidInputException("Invalid artist data");
        }
        artistRepo.addArtist(artist);
    }

    public List<String> getAllArtists() throws DatabaseOperationException {
        return artistRepo.getAllArtists();
    }
}
