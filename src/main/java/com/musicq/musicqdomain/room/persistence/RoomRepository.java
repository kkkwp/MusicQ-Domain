package com.musicq.musicqdomain.room.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicq.musicqdomain.room.domain.Room;
import com.musicq.musicqdomain.room.dto.RoomDto;

public interface RoomRepository extends JpaRepository<Room, Long> {

	default RoomDto entityToRoomDto(Room room) {
		RoomDto roomDto = RoomDto.builder()
			.room_id(room.getRoomId())
			.roomTitle(room.getRoomTitle())
			.gameName(room.getGameName())
			.build();

		return roomDto;
	}
}
