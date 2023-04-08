package com.musicq.musicqdomain.music.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicq.musicqdomain.music.domain.Music;
import com.musicq.musicqdomain.music.dto.MusicDto;
import com.musicq.musicqdomain.music.persistence.MusicRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/musics")
public class MusicController {

	private final MusicRepository musicRepository;

	@GetMapping("/all")
	public ResponseEntity<List<MusicDto>> searchAll() {
		List<Music> musicList = musicRepository.findAll();
		log.info(musicList);
		List<MusicDto> response = new ArrayList<>();
		for (Music music : musicList) {
			log.info(music);
			MusicDto musicDto = MusicDto.builder()
				.musicId(music.getMusicId())
				.videoId(music.getVideoId())
				.musicTitle(music.getMusicTitle())
				.singer(music.getSinger())
				.build();

			response.add(musicDto);
		}

		return ResponseEntity.ok(response);
	}
}
