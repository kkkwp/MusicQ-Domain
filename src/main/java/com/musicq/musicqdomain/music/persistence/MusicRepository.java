package com.musicq.musicqdomain.music.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicq.musicqdomain.music.domain.Music;

public interface MusicRepository extends JpaRepository<Music, Integer> {
}
