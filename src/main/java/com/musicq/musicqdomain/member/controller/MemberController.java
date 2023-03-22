package com.musicq.musicqdomain.member.controller;

import com.musicq.musicqdomain.member.domain.Member;
import com.musicq.musicqdomain.member.domain.MemberImage;
import com.musicq.musicqdomain.member.dto.LoginResDto;
import com.musicq.musicqdomain.member.dto.MemberInfoResDto;
import com.musicq.musicqdomain.member.persistence.MemberImageRepository;
import com.musicq.musicqdomain.member.persistence.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberImageRepository memberImageRepository;

    // JSON 으로 반환하기 위해 ResponseEntity<Object> 로 반환하고 count 라는 key 값을 갖도록 Map 을 만들어서 반환
    // ID 존재 여부
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> checkId(
            @Valid @PathVariable("id") String id
    ){
        Map<String, Long> response = new HashMap<>();
        long count = 0;
        try {
            count = memberRepository.countById(id);
        } catch (NullPointerException e){

            log.warn("Wrong ID");
        }

        response.put("count", count);
        log.warn(count);
        return ResponseEntity.ok(response);
    }

    // Email 존재 여부

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> checkEmail(
            @Valid @PathVariable("email") String email
    ){
        Map<String, Long> response = new HashMap<>();
        response.put("count", memberRepository.countByEmail(email));
        return ResponseEntity.ok(response);
    }

    // Nickname 존재 여부
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Object> checkNickname(
            @Valid @PathVariable("nickname") String nickname
    ){
        Map<String, Long> response = new HashMap<>();
        response.put("count", memberRepository.countByNickname(nickname));
        return ResponseEntity.ok(response);
    }

    // 회원 가입
    @PostMapping("/member")
    public ResponseEntity<MemberInfoResDto> signup(
            @Valid @RequestBody String memberInfo
    ){
        Map<String, Object> entityMap = memberRepository.memberInfoToEntity(memberInfo);
        Member member = (Member) entityMap.get("member");
        MemberImage memberImage = (MemberImage) entityMap.get("member_image");

        memberRepository.save(member);
        memberImageRepository.save(memberImage);

        MemberInfoResDto response = memberRepository.entityToMemberInfoRes(member, memberImage);
        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(
        @Valid @RequestBody String loginInfo
    ){
        JSONObject user = new JSONObject(loginInfo);

        Member member = memberRepository.findById(user.getString("id"));

        LoginResDto response = LoginResDto.builder()
            .id(member.getId())
            .email(member.getEmail())
            .nickname(member.getNickname()).build();
        return ResponseEntity.ok(response);
    }

    // 로그인 시 비밀번호 확인
    @GetMapping("/password/{id}")
    public ResponseEntity<Object> checkPassword(
        @Valid @PathVariable String id
    ){
        Member member = memberRepository.findById(id);
        String password = member.getPassword();

        Map<String, String> response = new HashMap<>();
        response.put("password", password);

        return ResponseEntity.ok(response);
    }
}
