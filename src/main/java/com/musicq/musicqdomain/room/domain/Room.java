package com.musicq.musicqdomain.room.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "room")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Room {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String roomId;

	private String roomTitle;

	private String gameName;

}
