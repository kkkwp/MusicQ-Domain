package com.musicq.musicqdomain.lyrics.persistence;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musicq.musicqdomain.lyrics.domain.Lyrics;
import com.musicq.musicqdomain.music.domain.Music;
import com.musicq.musicqdomain.music.persistence.MusicRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class LyricsRepositoryTest {
	@Autowired
	private LyricsRepository lyricsRepository;
	@Autowired
	private MusicRepository musicRepository;

	// 데이터 삽입 테스트
	@Test
	public void insert() {
		// dummy music
		// LongStream.rangeClosed(1, 10).forEach(i -> {
		// 		Music music = Music.builder()
		// 			.musicTitle("musicTitle" + i)
		// 			.videoId("videoId" + i)
		// 			.singer("singer" + i)
		// 			.build();
		//
		// 		musicRepository.save(music);
		// });

		// 삽입 테스트
		LongStream.rangeClosed(1, 10).forEach(i -> {
			List<Music> musicList = musicRepository.findAll();
			//System.out.println(musicList);

			Lyrics lyrics = Lyrics.builder()
				.musicLyrics("musicLyrics" + i)
				.music(musicList.get((int)i-1))
				.build();

			lyricsRepository.save(lyrics);
		});
	}

	// Music으로 가사 찾기 테스트
	@Test
	public void findOne() {
		// 임시 music 객체 생성
		Music music = Music.builder()
			.musicTitle("음악 제목이다")
			.videoId("영상 아이디다")
			.singer("나는 가수다")
			.build();

		musicRepository.save(music);

		// 테스트
		System.out.println(lyricsRepository.findByMusic(music));
	}
}