package com.musicq.musicqdomain.room.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// page와 size를 받아야 리턴 값으로 현재 페이지에 맞는 값을 줄 수 있다.
	// page는 코드 상에선 0부터 시작이지만
	// 사용자 관점에서는 1부터 시작이기에 -1 을 붙여주는 거 까먹지 말자.
	@GetMapping("/all")
	public ResponseEntity<Object> searchAll(
		@Valid @RequestParam(required = false, value = "page") Integer page
	) {
		// page는 0부터 시작
		page -= 1;

		// size는 6으로 고정 - 이유 : 6개의 그리드로 끊어서 페이지를 보여줄 것이기 때문이다.
		int size = 6;

		Map<String, Object> response = new HashMap<>();
		Pageable pageable = PageRequest.of(page, size, Sort.by("roomId").descending());
		Page<Room> allRooms = roomRepository.searchAll(pageable);

		response.put("totalPages", allRooms.getTotalElements());

		response.put("size", allRooms.getSize());

		response.put("number", allRooms.getNumber());

		response.put("previous", allRooms.hasPrevious());

		response.put("next", allRooms.hasNext());
		List<RoomDto> dtoList = allRooms.getContent()
			.stream()
			.map(room ->
				roomRepository.entityToRoomDto(room)
			)
			.collect(Collectors.toList());
		response.put("data", allRooms.getContent());

		return ResponseEntity.ok(response);
	}
}
