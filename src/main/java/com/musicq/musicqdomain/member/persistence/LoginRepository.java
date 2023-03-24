package com.musicq.musicqdomain.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicq.musicqdomain.member.domain.Member;
import com.musicq.musicqdomain.member.dto.LoginResDto;

public interface LoginRepository extends JpaRepository<Member, Long> {
	Member findById(String id);


	// login 처리 시 발급할 AccessToken에 삽입할 Member의 정보를 응답하기 위한 메서드
	default LoginResDto EntityToLoginRes(Member member){
		LoginResDto loginResDto = LoginResDto.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.build();

		return loginResDto;
	}
}
