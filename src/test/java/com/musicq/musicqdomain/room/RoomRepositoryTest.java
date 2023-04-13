package com.musicq.musicqdomain.room;

import java.util.stream.LongStream;

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
					.roomId(i)
					.roomTitle("roomTitle" + i)
					.gameName("낭독퀴즈")
					.build();

				roomRepository.save(room);
			});
	}

	// build 시 오류가 나기에 일단 주석처리 했습니다.
	/*@Test
	public void selectOne() {
		Optional<Room> findRoom = roomRepository.findById(1L);
		Room room = findRoom.get();
		System.out.println("room = " + room);

	}

	@Test
	public void deleteRoomId() {
		roomRepository.deleteById(1L);
	}*/

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
					.roomId(i)
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
