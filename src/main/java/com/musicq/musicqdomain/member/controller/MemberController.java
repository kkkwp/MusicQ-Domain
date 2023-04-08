package com.musicq.musicqdomain.member.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicq.musicqdomain.member.domain.Member;
import com.musicq.musicqdomain.member.domain.MemberImage;
import com.musicq.musicqdomain.member.dto.MemberInfoResDto;
import com.musicq.musicqdomain.member.persistence.MemberImageRepository;
import com.musicq.musicqdomain.member.persistence.MemberRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/members")
public class MemberController {
	private final MemberRepository memberRepository;
	private final MemberImageRepository memberImageRepository;

	// JSON 으로 반환하기 위해 ResponseEntity<Object> 로 반환하고 count 라는 key 값을 갖도록 Map 을 만들어서 반환
	// 회원 가입
	@PostMapping("/member")
	public ResponseEntity<MemberInfoResDto> controlMemberInfo(
		@Valid @RequestBody String memberInfo
	) {
		Map<String, Object> entityMap = memberRepository.memberInfoToEntity(memberInfo);
		Member member = (Member)entityMap.get("member");
		MemberImage memberImage = (MemberImage)entityMap.get("member_image");

		memberRepository.save(member);
		memberImageRepository.save(memberImage);

		MemberInfoResDto response = memberRepository.entityToMemberInfoRes(member, memberImage);
		return ResponseEntity.ok(response);
	}

	// 회원 정보 조회
	@GetMapping("/member/{id}")
	public ResponseEntity<MemberInfoResDto> checkMemberInfo(
		@Valid @PathVariable("id") String id
	) {
		Member member = memberRepository.findById(id);
		MemberImage memberImage = memberImageRepository.findAllByMember_Id(id);

		MemberInfoResDto response = memberRepository.entityToMemberInfoRes(member, memberImage);
		// 비밀 번호는 안보여 줄 거지롱 ~, 근데 Member_id(DB 기본키) 는 Client로 줘야 클라이언트가 가지고 있어야됨.
		// 클라이언트는 조회 후 수정 할 수 있기 때문에 일단 조회값으로 다 가지고 있다가 (물론 Client에 표시 하면 안됨) 수정 시 기본키 가진채로 요청
		// JPA는 save 함수가 기본키를 봤을 때 테이블에 존재하는 값이면 Update로 취급하거덩요
		// response.setPassword(null);
		return ResponseEntity.ok(response);
	}

	// 회원 정보 수정
	@PutMapping("/member/{id}")
	public ResponseEntity<MemberInfoResDto> changeMemberInfo(
		@Valid @PathVariable("id") String id,
		@Valid @RequestBody String memberInfo
	) {
		// 현재 입력 받은 id로 사용자의 정보를 가져와서 사용자가 입력한 MemberInfo에서 수정할
		// 요소들을 꺼내와서 id로 가져온 사용자 정보중 변경사항을 changes로 적용 후 꺼내온
		// 사용자의 정보를 다시 저장해서 Update되도록 구성
		Member existMember = memberRepository.findById(id);
		MemberImage existMemberImage = memberImageRepository.findAllByMember_Id(id);

		Map<String, Object> entityMap = memberRepository.memberInfoToEntity(memberInfo);
		Member member = (Member)entityMap.get("member");
		MemberImage memberImage = (MemberImage)entityMap.get("member_image");

		existMember.changesNickname(member.getNickname());
		existMemberImage.changesUuid(memberImage.getUuid());
		existMemberImage.changesPath(memberImage.getPath());
		existMemberImage.changesProfile_img(memberImage.getProfile_img());

		memberRepository.save(existMember);
		memberImageRepository.save(existMemberImage);

		Member changedMember = memberRepository.findById(id);
		MemberImage changedMemberImage = memberImageRepository.findAllByMember_Id(id);

		MemberInfoResDto response = memberRepository.entityToMemberInfoRes(changedMember, changedMemberImage);

		return ResponseEntity.ok(response);
	}

	// 회원 탈퇴
	@DeleteMapping("/member/{id}")
	public ResponseEntity<Object> unregister(
		@Valid @PathVariable("id") String id
	) {
		// JSON형태로 출력하기 위해서?
		Map<String, Long> response = new HashMap<>();

		// 현재 시간
		LocalDateTime now = LocalDateTime.now();

		// Soft Delete 방식
		// 회원 삭제
		Member member = memberRepository.findById(id);
		member.setDeletedAt(now);
		memberRepository.save(member);

		// 회원 이미지 정보 삭제
		MemberImage memberImage = memberImageRepository.findAllByMember_Id(id);
		memberImage.setDeletedAt(now);
		memberImageRepository.save(memberImage);

		long count = 0;
		// 탈퇴가 완료되었다면 count가 0이 나와야한다.
		try {
			count = memberRepository.countById(id);
		} catch (NullPointerException e) {
			log.warn("Not Exist ID");
		}

		response.put("count", count);
		log.warn(count);

		return ResponseEntity.ok(response);
	}

	// ID 존재 여부
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> checkId(
		@Valid @PathVariable("id") String id
	) {
		Map<String, Long> response = new HashMap<>();
		long count = 0;
		try {
			count = memberRepository.countById(id);
		} catch (NullPointerException e) {
			log.warn("Not Exist ID");
		}

		response.put("count", count);
		log.warn(count);
		return ResponseEntity.ok(response);
	}

	// Email 존재 여부
	@GetMapping("/email/{email}")
	public ResponseEntity<Object> checkEmail(
		@Valid @PathVariable("email") String email
	) {
		Map<String, Long> response = new HashMap<>();
		long count = 0;

		try {
			count = memberRepository.countByEmail(email);
		} catch (NullPointerException e) {
			log.warn("Not Exist Email");
		}

		response.put("count", count);
		return ResponseEntity.ok(response);
	}

	// Nickname 존재 여부
	@GetMapping("/nickname/{id}/{nickname}")
	public ResponseEntity<Object> checkNickname(
		@Valid @PathVariable("id") String id,
		@Valid @PathVariable("nickname") String nickname
	) {
		Map<String, String> response = new HashMap<>();
		String count = "0";
		String currentNickname = null;

		try {
			count = memberRepository.countByNickname(nickname);
			currentNickname = memberRepository.getCurrentNickname(id);
			log.warn(currentNickname);
		} catch (NullPointerException e) {
			log.warn("Not Exist Nickname");
		}

		response.put("count", count);
		response.put("currentNickname", currentNickname);
		return ResponseEntity.ok(response);
	}

}