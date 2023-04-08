package com.musicq.musicqdomain.music.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicq.musicqdomain.music.domain.Music;
import com.musicq.musicqdomain.music.dto.MusicDto;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {

	default MusicDto entityToDto(Music music) {
		MusicDto musicDto = MusicDto.builder()
			.musicId(music.getMusicId())
			.videoId(music.getVideoId())
			.musicTitle(music.getMusicTitle())
			.singer(music.getSinger())
			.build();

		return musicDto;
	}
}
