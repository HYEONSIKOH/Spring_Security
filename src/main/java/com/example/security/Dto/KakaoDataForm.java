package com.example.security.Dto;

import lombok.Getter;

@Getter
public class KakaoDataForm {
    long id;
    String nickname;
    String email;
    String profile_image;

    public KakaoDataForm (long id, String nickname, String email, String profile_image) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profile_image;
    }
}
