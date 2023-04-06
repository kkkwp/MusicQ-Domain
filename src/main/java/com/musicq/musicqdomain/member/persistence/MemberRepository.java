package com.musicq.musicqdomain.member.persistence;

import com.musicq.musicqdomain.member.domain.Member;
import com.musicq.musicqdomain.member.domain.MemberImage;
import com.musicq.musicqdomain.member.dto.MemberImageDto;
import com.musicq.musicqdomain.member.dto.MemberInfoResDto;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.Map;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // ID 존재 여부
    long countById(String id);

    // Email 존재 여부
    long countByEmail(String email);

    // nickname 존재 여부
    String countByNickname(String nickname);

    // 본인 nickname 을 변경하지 않고 바로 입력할 때 비교할 nickname 값이 필요하다.
    @Query("select m.nickname from Member m where m.id = :id")
    String getCurrentNickname(@Param("id") String id);

    // 회원 정보 조회
    Member findById(String id);

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

        JSONObject memberImageInfo = memberInfo.getJSONObject("memberImage");

        MemberImage memberImageDomain = MemberImage.builder()
                .uuid(memberImageInfo.getString("uuid"))
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
                .record(memberDomain.getRecord())
                .games_count(memberDomain.getGames_count())
                .win_count(memberDomain.getWin_count())
                .profile_img(memberImageDto)
                .build();

        return memberInfoResDto;
    }
}
