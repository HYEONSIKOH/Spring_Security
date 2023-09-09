package com.example.security.Dto;

import com.example.security.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class MemberForm {

    private String email;

    private String nickname;

    private String password;

    private int height;

    private int weight;

    public MemberForm(String email, String nickname, String password, int height, int weight) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }

    public Member toEntity() { return new Member(email, nickname, password, height, weight); }
}
