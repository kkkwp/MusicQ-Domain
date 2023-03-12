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
public class MemberInfoResDto {
    @NotNull
    private String id;

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    private Long record;

    @NotNull
    private int games_count;

    @NotNull
    private int win_count;

    @NotNull
    @Builder.Default
    private MemberImageDto profile_img =  new MemberImageDto();
}
