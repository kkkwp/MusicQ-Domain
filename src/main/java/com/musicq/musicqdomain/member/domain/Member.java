package com.musicq.musicqdomain.member.domain;

import com.musicq.musicqdomain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Log4j2
@ToString
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Where(clause = "deleted_at is NULL")
@SQLDelete(sql = "update member set deleted_at = CURRENT_TIMESTAMP where member_id = ?")
@Table(name = "member")
@Entity
public class Member extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false)
    @Builder.Default
    private Long record = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long games_count = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long win_count = 0L;

}
