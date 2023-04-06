package com.musicq.musicqdomain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private long record;

    @NotNull
    private long games_count;

    @NotNull
    private long win_count;

    @NotNull
    @Builder.Default
    private MemberImageDto profile_img =  new MemberImageDto();
}
