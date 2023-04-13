DROP TABLE IF EXISTS lyrics;
DROP TABLE IF EXISTS music;

CREATE TABLE music
(
    music_id    BIGINT NOT NULL AUTO_INCREMENT,
    music_title VARCHAR(255),
    singer      VARCHAR(255),
    video_id    VARCHAR(255),
    PRIMARY KEY (music_id)
) ENGINE = InnoDB;

CREATE TABLE lyrics
(
    lyrics_id    BIGINT NOT NULL AUTO_INCREMENT,
    music_lyrics TEXT   NOT NULL,
    music_id BIGINT NOT NULL,
    FOREIGN KEY (music_id) REFERENCES music (music_id),
    PRIMARY KEY (lyrics_id)
) ENGINE = InnoDB;