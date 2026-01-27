A. Project Overview
This project is a Java-based Music Library Management API that simulates a simple backend for managing songs, playlists, artists, and albums.
It connects to a PostgreSQL database using JDBC and demonstrates a full multi-layer architecture:
controller → service → repository → model.
The purpose is to demonstrate:
Encapsulation, inheritance, and polymorphism in an abstract song hierarchy.
Interfaces for validation and playback.
Database operations through JDBC with PreparedStatements.
Exception handling using a custom exception hierarchy.
Clear separation of responsibilities between layers.
Entities and Relationships
Artist — represents a musician or band.
Album — represents a music album.
SongBase (abstract) — parent for different song types (e.g. MetalSong, PopSong, JazzSong).
Playlist — aggregates multiple songs (composition relationship).
Relationships:
One Artist → Many Songs
One Album → Many Songs
Many Songs ↔ Many Playlists

B. OOP Design Documentation
Abstract Class
SongBase (abstract)
Fields: id, name, artist, album, genre, duration, releaseDate.
Abstract methods:
MusicPlay()
getGenreType()
Concrete methods:
play() (from interface)
isValid() (from interface)
showInfo()
Subclasses
MetalSong
PopSong
JazzSong
Each subclass overrides MusicPlay() and getGenreType() to provide different behavior.
Example of polymorphism:
SongBase song = new MetalSong(...);
song.MusicPlay();
Interfaces
Playable — defines void play()
Validatable — defines boolean isValid()
Implemented in model classes (SongBase, Playlist, Artist, Album).
Composition / Aggregation
A Playlist contains a List<SongBase> (composition).
Removing the playlist removes its contained songs association.
Polymorphism Example
All song types (MetalSong, PopSong, JazzSong) can be treated as SongBase and stored together in one Playlist.
UML Diagram (text summary)
Playable, Validatable
        ↑
     SongBase (abstract)
        ↑
 ┌─────────────┬───────────────┬──────────────┐
 │  MetalSong  │   PopSong     │   JazzSong   │
 └─────────────┴───────────────┴──────────────┘
Playlist → contains → List<SongBase>
Artist  → hasMany → SongBase
Album   → hasMany → SongBase

C. Database Description
Schema
Tables:
artists(id SERIAL PRIMARY KEY, name VARCHAR(100) UNIQUE, country VARCHAR(50))
albums(id SERIAL PRIMARY KEY, title VARCHAR(100) UNIQUE, release_year INT)
songs(id SERIAL PRIMARY KEY, name VARCHAR(100), artist VARCHAR(100), album VARCHAR(100), genre VARCHAR(50), duration INT, release_date DATE)
playlists(id SERIAL PRIMARY KEY, name VARCHAR(100) UNIQUE)
playlist_songs(playlist_id INT REFERENCES playlists(id), song_id INT REFERENCES songs(id), PRIMARY KEY (playlist_id, song_id))
Foreign Keys
playlist_songs.playlist_id → playlists.id
playlist_songs.song_id → songs.id
Sample Inserts
INSERT INTO artists (name, country) VALUES ('Metallica', 'USA'), ('Black Sabbath', 'UK');
INSERT INTO albums (title, release_year) VALUES ('Master of Puppets', 1986), ('Paranoid', 1970);
INSERT INTO songs (name, artist, album, genre, duration, release_date)
VALUES ('Master of Puppets', 'Metallica', 'Master of Puppets', 'Metal', 515, '1986-03-03'),
       ('War Pigs', 'Black Sabbath', 'Paranoid', 'Metal', 470, '1970-09-18');
INSERT INTO playlists (name) VALUES ('Heavy Legends');
INSERT INTO playlist_songs (playlist_id, song_id) VALUES (1, 1), (1, 2);

D. Controller
Main.java acts as a simple controller demonstrating CRUD operations for each entity.
Example sequence:
Create artists, albums, and songs through respective services.
Create playlist and add songs to it.
Display all songs (read).
Update or delete a song.
Trigger validation and duplicate checks to show exception handling.
Each operation uses the service layer, which handles validation and communicates with repositories.
E. Instructions to Compile and Run
Compile
javac -d out -cp "lib/postgresql.jar" src/**/*.java
Run
java -cp "out;lib/postgresql.jar" controller.Main
Ensure that:
PostgreSQL is running on port 5432.
Database music_library exists.
All tables are created from the schema above.
DatabaseConnection.java contains the correct URL, username, and password.
F. Screenshots
Screenshots should include:
Successful connection to database (Connected to PostgreSQL...).
Creation of multiple songs and playlists.
Output of getAll() methods listing all songs, artists, and albums.
Example of validation or duplicate exception (Error: Song already exists).
CLI output of playlist playback.
Place screenshots under:

G. Reflection Section
What I Learned
How to structure a Java project into controller, service, repository, and model layers.
Implementing JDBC safely using PreparedStatement and connection management.
Designing custom exception hierarchies for clean error handling.
Applying OOP principles (abstraction, inheritance, encapsulation, polymorphism).
Managing relational integrity between multiple entities (songs, artists, playlists).
Challenges Faced
Ensuring all validation and CRUD logic remained only in the service layer.
Managing PostgreSQL connections and SQL exceptions cleanly.
Avoiding duplicate handling between repository and service.
Structuring packages consistently to satisfy architectural requirements.
Benefits of JDBC and Multi-Layer Design
Clear separation of logic improves maintainability and testing.
JDBC provides direct SQL control and portability across databases.

Services abstract complex business rules away from low-level database access.

Easier debugging and modular growth for future REST API expansion.
