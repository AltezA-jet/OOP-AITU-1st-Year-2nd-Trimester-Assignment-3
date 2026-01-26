-- ========================
-- DATABASE CREATION
-- ========================
-- CREATE DATABASE music_library;
-- \c music_library;

-- ========================
-- TABLES
-- ========================

CREATE TABLE songs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    artist VARCHAR(100) NOT NULL,
    album VARCHAR(100),
    genre VARCHAR(50),
    duration INT CHECK (duration > 0),
    release_date DATE NOT NULL,
    has_guitar_solo BOOLEAN,
    bpm INT,
    is_heavy BOOLEAN,
    has_improvisation BOOLEAN,
    instrument_focus VARCHAR(50),
    mood VARCHAR(50),
    danceable BOOLEAN,
    chart_position INT,
    producer VARCHAR(100)
);

CREATE TABLE playlists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE playlist_songs (
    playlist_id INT REFERENCES playlists(id) ON DELETE CASCADE,
    song_id INT REFERENCES songs(id) ON DELETE CASCADE,
    PRIMARY KEY (playlist_id, song_id)
);

-- ========================
-- INSERT PLAYLISTS
-- ========================
INSERT INTO playlists (name) VALUES
('Metal Classics'),
('Jazz Lounge'),
('Pop Hits');

-- ========================
-- INSERT METAL SONGS (10)
-- ========================
INSERT INTO songs (name, artist, album, genre, duration, release_date, has_guitar_solo, bpm, is_heavy)
VALUES
('Master of Puppets', 'Metallica', 'Master of Puppets', 'Metal', 515, '1986-03-03', TRUE, 220, TRUE),
('Enter Sandman', 'Metallica', 'Metallica', 'Metal', 331, '1991-07-29', TRUE, 123, TRUE),
('Painkiller', 'Judas Priest', 'Painkiller', 'Metal', 345, '1990-09-03', TRUE, 180, TRUE),
('The Trooper', 'Iron Maiden', 'Piece of Mind', 'Metal', 250, '1983-06-20', TRUE, 156, TRUE),
('Holy Wars', 'Megadeth', 'Rust in Peace', 'Metal', 360, '1990-08-14', TRUE, 140, TRUE),
('Fear of the Dark', 'Iron Maiden', 'Fear of the Dark', 'Metal', 438, '1992-05-11', FALSE, 132, TRUE),
('One', 'Metallica', '…And Justice for All', 'Metal', 447, '1988-01-10', TRUE, 140, TRUE),
('Raining Blood', 'Slayer', 'Reign in Blood', 'Metal', 290, '1986-10-07', TRUE, 220, TRUE),
('Hallowed Be Thy Name', 'Iron Maiden', 'Number of the Beast', 'Metal', 425, '1982-07-22', FALSE, 155, TRUE),
('Chop Suey!', 'System of a Down', 'Toxicity', 'Metal', 210, '2001-08-27', FALSE, 140, TRUE);

-- ========================
-- INSERT JAZZ SONGS (10)
-- ========================
INSERT INTO songs (name, artist, album, genre, duration, release_date, has_improvisation, instrument_focus, mood)
VALUES
('Take Five', 'Dave Brubeck', 'Time Out', 'Jazz', 324, '1959-12-14', TRUE, 'Saxophone', 'Cool'),
('So What', 'Miles Davis', 'Kind of Blue', 'Jazz', 545, '1959-08-17', TRUE, 'Trumpet', 'Relaxed'),
('My Favorite Things', 'John Coltrane', 'My Favorite Things', 'Jazz', 800, '1961-03-01', TRUE, 'Saxophone', 'Uplifting'),
('Round Midnight', 'Thelonious Monk', 'Round Midnight', 'Jazz', 300, '1947-11-21', TRUE, 'Piano', 'Melancholic'),
('All Blues', 'Miles Davis', 'Kind of Blue', 'Jazz', 690, '1959-08-17', FALSE, 'Trumpet', 'Cool'),
('Blue in Green', 'Miles Davis', 'Kind of Blue', 'Jazz', 330, '1959-08-17', TRUE, 'Piano', 'Relaxed'),
('A Night in Tunisia', 'Dizzy Gillespie', 'Dizzy Gillespie', 'Jazz', 420, '1946-01-01', TRUE, 'Trumpet', 'Energetic'),
('Misty', 'Erroll Garner', 'Misty', 'Jazz', 240, '1954-06-01', FALSE, 'Piano', 'Romantic'),
('Freddie Freeloader', 'Miles Davis', 'Kind of Blue', 'Jazz', 400, '1959-08-17', TRUE, 'Piano', 'Smooth'),
('Cantaloupe Island', 'Herbie Hancock', 'Empyrean Isles', 'Jazz', 330, '1964-12-01', FALSE, 'Piano', 'Groovy');

-- ========================
-- INSERT POP SONGS (10)
-- ========================
INSERT INTO songs (name, artist, album, genre, duration, release_date, danceable, chart_position, producer)
VALUES
('Billie Jean', 'Michael Jackson', 'Thriller', 'Pop', 294, '1983-01-02', TRUE, 1, 'Quincy Jones'),
('Like a Prayer', 'Madonna', 'Like a Prayer', 'Pop', 342, '1989-03-21', TRUE, 1, 'Patrick Leonard'),
('Rolling in the Deep', 'Adele', '21', 'Pop', 228, '2010-11-29', TRUE, 1, 'Paul Epworth'),
('Shape of You', 'Ed Sheeran', 'Divide', 'Pop', 233, '2017-01-06', TRUE, 1, 'Steve Mac'),
('Uptown Funk', 'Mark Ronson', 'Uptown Funk', 'Pop', 270, '2014-11-10', TRUE, 1, 'Jeff Bhasker'),
('Happy', 'Pharrell Williams', 'G I R L', 'Pop', 233, '2013-11-21', TRUE, 1, 'Pharrell Williams'),
('Firework', 'Katy Perry', 'Teenage Dream', 'Pop', 228, '2010-10-26', TRUE, 1, 'Dr. Luke'),
('Blinding Lights', 'The Weeknd', 'After Hours', 'Pop', 200, '2019-11-29', TRUE, 1, 'Max Martin'),
('Shake It Off', 'Taylor Swift', '1989', 'Pop', 242, '2014-08-18', TRUE, 1, 'Max Martin'),
('Poker Face', 'Lady Gaga', 'The Fame', 'Pop', 235, '2008-09-26', TRUE, 1, 'RedOne');

-- ========================
-- LINK SONGS TO PLAYLISTS
-- ========================
-- Metal Classics = playlist_id 1
INSERT INTO playlist_songs (playlist_id, song_id)
SELECT 1, id FROM songs WHERE genre='Metal';

-- Jazz Lounge = playlist_id 2
INSERT INTO playlist_songs (playlist_id, song_id)
SELECT 2, id FROM songs WHERE genre='Jazz';

-- Pop Hits = playlist_id 3
INSERT INTO playlist_songs (playlist_id, song_id)
SELECT 3, id FROM songs WHERE genre='Pop';

CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

CREATE TABLE albums (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    release_year INT CHECK (release_year > 1900)
);

