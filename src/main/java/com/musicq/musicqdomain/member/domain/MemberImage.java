package com.musicq.musicqdomain.member.domain;

import com.musicq.musicqdomain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString(exclude = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "member_image")
@Entity
public class MemberImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid; // 파일 이름이 겹치지 않도록 하기 위해서 추가적으로 붙일 uuid
    private String profile_img; // 파일 이름

    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changesUuid(String uuid){
        this.uuid = uuid;
    }

    public void changesProfile_img(String profile_img){
        this.profile_img = profile_img;
    }

    public void changesPath(String path){
        this.path = path;
    }
}
