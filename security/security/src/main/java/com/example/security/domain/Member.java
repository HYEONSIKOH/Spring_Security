package com.example.security.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(columnDefinition = "varchar (270) default 'EMPTY'")
    private String email;

    private String nickname;

    private String password;

    private int height;

    private int weight;

    public Member(String email, String nickname, String password, int height, int weight) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }
}
