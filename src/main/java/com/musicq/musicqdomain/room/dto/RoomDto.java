package com.musicq.musicqdomain.room.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

	@NotNull
	private String roomId;

	@NotNull
	private String roomTitle;

	@NotNull
	private String gameName;
}
