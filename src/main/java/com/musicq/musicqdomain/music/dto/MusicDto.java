package com.musicq.musicqdomain.music.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDto {
	private String musicLink;

	private String musicTitle;

	private String singer;
}
