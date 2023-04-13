package com.musicq.musicqdomain.lyrics.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicq.musicqdomain.lyrics.domain.Lyrics;
import com.musicq.musicqdomain.lyrics.dto.LyricsDto;
import com.musicq.musicqdomain.music.domain.Music;

public interface LyricsRepository extends JpaRepository<Lyrics, Long> {
	Lyrics findByMusic(Music music);

	default LyricsDto entityToDto(Lyrics lyrics) {
		LyricsDto lyricsDto = LyricsDto.builder()
			.lyricsId(lyrics.getLyricsId())
			.musicLyrics(lyrics.getMusicLyrics())
			.music(lyrics.getMusic())
			.build();

		return lyricsDto;
	}
}
