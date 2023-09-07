package com.example.security.Dto;

import com.example.security.domain.Member;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@NoArgsConstructor
public class MemberForm {

    private Long id;

    private String email;

    private String nickname;

    private String password;

    private int height;

    private int weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public MemberForm(Long id, String email, String nickname, String password, int height, int weight) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }

    public Member toEntity() { return new Member(id, email, nickname, password, height, weight); }
}
