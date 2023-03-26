package com.musicq.musicqdomain.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicq.musicqdomain.member.domain.Member;
import com.musicq.musicqdomain.member.dto.LoginResDto;
import com.musicq.musicqdomain.member.persistence.LoginRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/members")
public class LoginController {
	private final LoginRepository loginRepository;

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<LoginResDto> login(
		@Valid @RequestBody String loginInfo
	){
		JSONObject logininfo = new JSONObject(loginInfo);

		Member member = loginRepository.findById(logininfo.getString("id"));

		LoginResDto response = loginRepository.entityToLoginRes(member);

		return ResponseEntity.ok(response);
	}

	// 로그인 시 비밀번호 확인
	@GetMapping("/password/{id}")
	public ResponseEntity<Object> checkPassword(
		@Valid @PathVariable String id
	){
		Member member = loginRepository.findById(id);
		String password = member.getPassword();

		Map<String, String> response = new HashMap<>();
		response.put("password", password);

		return ResponseEntity.ok(response);
	}
}
