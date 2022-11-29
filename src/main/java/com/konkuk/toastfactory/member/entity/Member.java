package com.konkuk.toastfactory.member.entity;

import com.konkuk.toastfactory.common.BasicEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected 기본 생성자를 추가해줌 (JPA 규약 상 의무적으로 필요함)
public class Member extends BasicEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_idx")
    private Long idx;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private Long score;

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0L;
    }
}
