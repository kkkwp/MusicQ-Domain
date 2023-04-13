package com.musicq.musicqdomain.lyrics.dto;

import com.musicq.musicqdomain.lyrics.domain.Lyrics;
import com.musicq.musicqdomain.music.domain.Music;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LyricsDto {
	@NotNull
	private Long lyricsId;
	@NotNull
	private String musicLyrics;
	@NotNull
	private Music music;

	public LyricsDto(Lyrics lyrics) {
		lyricsId = lyrics.getLyricsId();
		musicLyrics = lyrics.getMusicLyrics();
		music = lyrics.getMusic();
	}
}
