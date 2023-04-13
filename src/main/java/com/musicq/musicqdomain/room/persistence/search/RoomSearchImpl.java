package com.musicq.musicqdomain.room.persistence.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.musicq.musicqdomain.room.domain.QRoom;
import com.musicq.musicqdomain.room.domain.Room;
import com.querydsl.jpa.JPQLQuery;

public class RoomSearchImpl extends QuerydslRepositorySupport implements RoomSearch {

	public RoomSearchImpl() {
		super(Room.class);
	}

	@Override
	public Page<Room> searchAll(Pageable pageable) {
		QRoom room = QRoom.room;
		JPQLQuery<Room> query = from(room);

		this.getQuerydsl().applyPagination(pageable, query);

		// JPQLQuery의 실행은 fetch() 라는 기능을 이용
		List<Room> list = query.fetch();

		// fetchCount() 를 이용하면 count 쿼리를 실행할 수 있다.
		long count = query.fetchCount();

		return new PageImpl<>(list, pageable, count);
	}
}
