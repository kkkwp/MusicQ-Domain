package com.musicq.musicqdomain.music.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDto {

	@NotNull
	private Long musicId;
	@NotNull
	private String videoId;
	@NotNull
	private String musicTitle;
	@NotNull
	private String singer;
}
