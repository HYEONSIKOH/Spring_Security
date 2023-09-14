package com.example.security.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckPwForm {
    private String nickname;
    private String email;

    public CheckPwForm(String nickname, String email) {
        this.email = email;
        this.nickname = nickname;
    }

}
