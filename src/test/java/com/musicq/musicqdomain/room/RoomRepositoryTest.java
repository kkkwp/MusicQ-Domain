package com.musicq.musicqdomain.room;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musicq.musicqdomain.room.domain.Room;
import com.musicq.musicqdomain.room.persistence.RoomRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class RoomRepositoryTest {

	@Autowired
	private RoomRepository roomRepository;

	@Test
	public void insert() {
		LongStream.rangeClosed(1, 10)
			.forEach(i -> {
				Room room = Room.builder()
					.roomId(i)
					.roomTitle("roomTitle" + i)
					.gameName("낭독퀴즈")
					.build();

				roomRepository.save(room);
			});
	}

	@Test
	public void selectOne() {
		Optional<Room> findRoom = roomRepository.findById(11L);
		Room room = findRoom.get();
		System.out.println("room = " + room);

	}

	@Test
	public void deleteRoomId() {
		roomRepository.deleteById(12L);
	}
}
