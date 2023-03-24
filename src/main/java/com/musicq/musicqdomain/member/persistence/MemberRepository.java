package com.musicq.musicqdomain.member.persistence;

import com.musicq.musicqdomain.member.domain.Member;
import com.musicq.musicqdomain.member.domain.MemberImage;
import com.musicq.musicqdomain.member.dto.LoginResDto;
import com.musicq.musicqdomain.member.dto.MemberImageDto;
import com.musicq.musicqdomain.member.dto.MemberInfoResDto;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // ID 존재 여부
    long countById(String id);

    // Email 존재 여부
    Long countByEmail(String email);

    // nickname 존재 여부
    Long countByNickname(String nickname);

    default Map<String, Object> memberInfoToEntity(String memberInfoReq){
        Map<String, Object> entityMap = new HashMap<>();
        JSONObject memberInfo = new JSONObject(memberInfoReq);

        Member memberDomain = Member.builder()
                .id(memberInfo.getString("id"))
                .email(memberInfo.getString("email"))
                .nickname(memberInfo.getString("nickname"))
                .password(memberInfo.getString("password"))
                .build();

        entityMap.put("member", memberDomain);

        JSONObject memberImageInfo = memberInfo.getJSONObject("memberImageDto");

        MemberImage memberImageDomain = MemberImage.builder()
                //TODO 추 후 Service Application 에서 UUID 생성하면 주석제거
                //.uuid(memberImageInfo.getString("uuid"))
                .path(memberImageInfo.getString("path"))
                .profile_img(memberImageInfo.getString("profile_img"))
                .member(memberDomain)
                .build();
        entityMap.put("member_image", memberImageDomain);

        return entityMap;
    }

    default MemberInfoResDto entityToMemberInfoRes(Member memberDomain, MemberImage memberImageDomain){

        MemberImageDto memberImageDto = MemberImageDto.builder()
                .uuid(memberImageDomain.getUuid())
                .path(memberImageDomain.getPath())
                .profile_img(memberImageDomain.getProfile_img())
                .build();

        MemberInfoResDto memberInfoResDto = MemberInfoResDto.builder()
                .id(memberDomain.getId())
                .email(memberDomain.getEmail())
                .nickname(memberDomain.getNickname())
                .password(memberDomain.getPassword())
                .record(memberDomain.getRecord())
                .games_count(memberDomain.getGames_count())
                .win_count(memberDomain.getWin_count())
                .profile_img(memberImageDto)
                .build();

        return memberInfoResDto;
    }
}
