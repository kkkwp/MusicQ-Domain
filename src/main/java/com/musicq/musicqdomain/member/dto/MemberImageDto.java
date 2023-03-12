package com.musicq.musicqdomain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberImageDto {

    private String uuid;

    private String path;

    private String profile_img;
}
