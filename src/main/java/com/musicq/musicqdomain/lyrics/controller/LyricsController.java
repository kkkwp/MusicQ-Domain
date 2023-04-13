package com.musicq.musicqdomain.lyrics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musicq.musicqdomain.lyrics.domain.Lyrics;
import com.musicq.musicqdomain.lyrics.dto.LyricsDto;
import com.musicq.musicqdomain.lyrics.persistence.LyricsRepository;
import com.musicq.musicqdomain.music.domain.Music;
import com.musicq.musicqdomain.music.persistence.MusicRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/lyrics")
public class LyricsController {
	private final MusicRepository musicRepository;
	private final LyricsRepository lyricsRepository;

	@GetMapping("/")
	public ResponseEntity<Object> search(
		@RequestParam(name = "id") String[] musicIdList
	) {
		Map<String, String> response = new HashMap<>();

		for(String musicId : musicIdList) {
			// id로 Music를 찾는다.
			Music music = musicRepository.findByMusicId(musicId);
			// Music으로 Lyrics를 찾는다.
			Lyrics lyrics = lyricsRepository.findByMusic(music);
			// Lyrics에서 musicLyrics를 찾아 add
			response.put(musicId, lyrics.getMusicLyrics());
		}
		return ResponseEntity.ok(response);
	}
}
