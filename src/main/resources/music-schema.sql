DROP TABLE IF EXISTS music;

CREATE TABLE music
(
    music_id    BIGINT NOT NULL AUTO_INCREMENT,
    music_title VARCHAR(255),
    singer      VARCHAR(255),
    video_id    VARCHAR(255),
    PRIMARY KEY (music_id)
) ENGINE = InnoDB;