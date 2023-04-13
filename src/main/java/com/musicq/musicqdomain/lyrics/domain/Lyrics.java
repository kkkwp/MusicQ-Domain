package com.musicq.musicqdomain.lyrics.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.musicq.musicqdomain.music.domain.Music;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "lyrics")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Lyrics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lyricsId;
	private String musicLyrics;
	@OneToOne
	@JoinColumn(name = "music_id")
	private Music music;
}
