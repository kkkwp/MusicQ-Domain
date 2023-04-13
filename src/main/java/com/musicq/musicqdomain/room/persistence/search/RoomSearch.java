package com.musicq.musicqdomain.room.persistence.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.musicq.musicqdomain.room.domain.Room;

public interface RoomSearch {

	/**
	 *
	 * @param pageable - 한 페이지에 몇 개 사용할 지 보여주기
	 * @return
	 */
	Page<Room> searchAll(Pageable pageable);
}
