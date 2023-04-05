package com.musicq.musicqdomain.music.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.musicq.musicqdomain.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "music")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Music {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long musicId;

	private String musicLink;

	private String musicTitle;

	private String singer;
}
