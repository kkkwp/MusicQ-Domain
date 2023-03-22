package com.musicq.musicqdomain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginResDto {
	@NotNull
	private String id;

	@NotNull
	private String email;

	@NotNull
	private String nickname;
}
