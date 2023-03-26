package com.musicq.musicqdomain.member.persistence;

import com.musicq.musicqdomain.member.domain.MemberImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberImageRepository extends JpaRepository<MemberImage, Long> {
	MemberImage findAllByMember_Id(String id);
}
