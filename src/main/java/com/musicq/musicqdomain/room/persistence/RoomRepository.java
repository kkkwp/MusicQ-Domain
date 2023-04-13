package com.musicq.musicqdomain.room.persistence;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;

import com.musicq.musicqdomain.room.domain.Room;
import com.musicq.musicqdomain.room.dto.RoomDto;
import com.musicq.musicqdomain.room.persistence.search.RoomSearch;

public interface RoomRepository extends JpaRepository<Room, String>, RoomSearch {

	default Room roomInfoToEntity(String roomInfoReq, String sessionID) {
		JSONObject jsonRoomInfo = new JSONObject(roomInfoReq);
		Room room = Room.builder()
			.roomId(sessionID)
			.roomTitle(jsonRoomInfo.getString("roomTitle"))
			.gameName(jsonRoomInfo.getString("gameName"))
			.build();
		return room;
	}

	default RoomDto entityToRoomDto(Room room) {
		RoomDto roomDto = RoomDto.builder()
			.roomId(room.getRoomId())
			.roomTitle(room.getRoomTitle())
			.gameName(room.getGameName())
			.build();

		return roomDto;
	}
}
