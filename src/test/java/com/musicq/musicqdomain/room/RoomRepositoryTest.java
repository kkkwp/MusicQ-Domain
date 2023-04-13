package com.musicq.musicqdomain.room;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
					.roomId("ses_" + i)
					.roomTitle("roomTitle" + i)
					.gameName("낭독퀴즈")
					.build();
				roomRepository.save(room);
			});
	}

	@Test
	public void selectOne() {
		Optional<Room> findRoom = roomRepository.findById("ses_7");
		Room room = findRoom.get();
		Assertions.assertEquals(room.getRoomTitle(), "roomTitle7");
	}

	@Test
	public void deleteRoomId() {
		roomRepository.deleteById("ses_7");
	}

	// TODO: 방 정렬 기준을 createAt으로 바꾸기...?
	@Test
	public void testSearchAll() {
		Pageable pageable = PageRequest.of(0, 6, Sort.by("roomId").descending());

		Page<Room> result = roomRepository.searchAll(pageable);
	}

	@Test
	public void testSearchAll2() {

		LongStream.rangeClosed(1, 90)
			.forEach(i -> {
				Room room = Room.builder()
					.roomId("ses_" + i)
					.roomTitle("roomTitle" + i)
					.gameName("낭독퀴즈")
					.build();

				roomRepository.save(room);
			});

		Pageable pageable = PageRequest.of(0, 6, Sort.by("roomId").descending());

		Page<Room> result = roomRepository.searchAll(pageable);
		log.info("result : {}", result);

		// total pages
		log.info(result.getTotalPages());

		// page size
		log.info(result.getSize());

		// pageNumber
		log.info(result.getNumber());

		// prev next
		log.info(result.hasPrevious() + ": " + result.hasNext());
		result.getContent().forEach(room -> log.info(room));
	}
}
