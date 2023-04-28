CREATE TABLE music
(
    music_id    BIGINT NOT NULL AUTO_INCREMENT,
    music_title VARCHAR(255),
    singer      VARCHAR(255),
    video_id    VARCHAR(255),
    PRIMARY KEY (music_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE lyrics
(
    lyrics_id    BIGINT NOT NULL AUTO_INCREMENT,
    music_lyrics TEXT   NOT NULL,
    music_id     BIGINT NOT NULL,
    FOREIGN KEY (music_id) REFERENCES music (music_id),
    PRIMARY KEY (lyrics_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE room
(
    room_id    VARCHAR(255) NOT NULL,
    room_title VARCHAR(255),
    game_name  VARCHAR(255),
    PRIMARY KEY (room_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE member
(
    member_id   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    id          VARCHAR(255)        NOT NULL UNIQUE,
    email       VARCHAR(255)        NOT NULL UNIQUE,
    nickname    VARCHAR(255)        NOT NULL UNIQUE,
    password    VARCHAR(255)        NOT NULL,
    record      BIGINT(20)          NOT NULL DEFAULT 0,
    games_count BIGINT(20)          NOT NULL DEFAULT 0,
    win_count   BIGINT(20)          NOT NULL DEFAULT 0,
    created_at  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMP           NULL     DEFAULT NULL,
    PRIMARY KEY (member_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE member_image
(
    member_image_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    uuid            VARCHAR(255)    NOT NULL,
    profile_img     VARCHAR(255)    NOT NULL,
    path            VARCHAR(255)    NOT NULL,
    created_at      DATETIME        NOT NULL,
    updated_at      DATETIME        NOT NULL,
    deleted_at      DATETIME,
    member_id       BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (member_image_id),
    CONSTRAINT fk_member_id FOREIGN KEY (member_id)
        REFERENCES member (member_id)
        ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;