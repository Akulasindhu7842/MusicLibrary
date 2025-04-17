CREATE DATABASE MusicLibrary;
USE MusicLibrary;

-- Artists Table
CREATE TABLE  Artists (
    artist_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50)
);
select * from Artists;

-- Albums Table
CREATE TABLE Albums (
    album_id INT AUTO_INCREMENT PRIMARY KEY,
    artist_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    release_year INT,
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id) ON DELETE CASCADE
);
select * from Albums;

-- Songs Table
CREATE TABLE Songs (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    album_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    duration TIME,
    play_count INT DEFAULT 0,
    FOREIGN KEY (album_id) REFERENCES Albums(album_id) ON DELETE CASCADE
);
select * from Songs;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
select * from Users;

CREATE TABLE Playlists (
    playlist_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
select * from Playlists;

CREATE TABLE PlaylistSongs (
    playlist_id INT,
    song_id INT,
    FOREIGN KEY (playlist_id) REFERENCES Playlists(playlist_id),
    FOREIGN KEY (song_id) REFERENCES Songs(song_id),
    PRIMARY KEY (playlist_id, song_id)
);
select * from PlaylistSongs;
