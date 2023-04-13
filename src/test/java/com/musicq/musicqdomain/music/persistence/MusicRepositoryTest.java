package com.musicq.musicqdomain.music.persistence;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musicq.musicqdomain.music.domain.Music;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MusicRepositoryTest {
	@Autowired
	private MusicRepository musicRepository;

	// 데이터 삽입 테스트
	@Test
	public void insert() {
		LongStream.rangeClosed(1, 10).forEach(i -> {
			Music music = Music.builder()
				.musicTitle("musicTitle" + i)
				.videoId("videoId" + i)
				.singer("singer" + i)
				.build();

			musicRepository.save(music);
		});
	}

	// 전체 찾기 테스트
	@Test
	public void searchAll() {
		System.out.println(musicRepository.findAll());
	}

	// id로 Music 찾기 테스트
	@Test
	public void findOne() {
		// 임시 music 객체 생성
		Music music = Music.builder()
			.musicTitle("음악 제목이다")
			.videoId("영상 아이디다")
			.singer("나는 가수다")
			.build();

		musicRepository.save(music);

		String id = String.valueOf(music.getMusicId());
		System.out.println(musicRepository.findByMusicId(id));
	}
}
