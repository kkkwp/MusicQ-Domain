package com.musicq.musicqdomain.room.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	// 단일 방 정보 조회 - 방 입장을 위해 사용
	@GetMapping("/enter/{roomId}")
	public ResponseEntity<Object> roomEnter(@Valid @PathVariable("roomId") Long roomId) {
		Optional<Room> findRoom = roomRepository.findById(roomId);

		Room room = findRoom.get();
		RoomDto response = roomRepository.entityToRoomDto(room);

		return ResponseEntity.ok(response);
	}

	// 방 삭제
	@DeleteMapping("/delete/{roomId}")
	public ResponseEntity<Object> deleteRoom(@Valid @PathVariable("roomId") Long roomId) {
		Map<String, Long> response = new HashMap<>();

		Optional<Room> findRoom = roomRepository.findById(roomId);

		if (findRoom.isPresent()) {
			roomRepository.deleteById(roomId);
			response.put("result", 1L);

			return ResponseEntity.ok(response);
		}
		response.put("result", 0L);
		return ResponseEntity.ok(response);

	}
}
