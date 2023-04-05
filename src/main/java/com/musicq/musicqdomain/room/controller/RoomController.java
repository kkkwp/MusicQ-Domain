package com.musicq.musicqdomain.room.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicq.musicqdomain.room.domain.Room;
import com.musicq.musicqdomain.room.dto.RoomDto;
import com.musicq.musicqdomain.room.persistence.RoomRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/rooms")
public class RoomController {
	private final RoomRepository roomRepository;

	@GetMapping("/enter/{roomId}")
	public ResponseEntity<Object> roomEnter(
		@Valid @PathVariable("roomId") String roomId
	) {
		Room room = roomRepository.findByRoomId(roomId);

		RoomDto response = roomRepository.entityToRoomDto(room);

		return ResponseEntity.ok(response);
	}
}
