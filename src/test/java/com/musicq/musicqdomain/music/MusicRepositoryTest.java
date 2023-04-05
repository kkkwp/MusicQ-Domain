package com.musicq.musicqdomain.music;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musicq.musicqdomain.music.domain.Music;
import com.musicq.musicqdomain.music.persistence.MusicRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MusicRepositoryTest {

	@Autowired
	private MusicRepository musicRepository;

	@Test
	public void insert() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			Music music = Music.builder()
				.musicTitle("musicTitle" + i)
				.musicLink("videoId" + i)
				.singer("singer" + i)
				.build();

			musicRepository.save(music);
		});
	}
}
