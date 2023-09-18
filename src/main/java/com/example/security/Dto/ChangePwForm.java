package com.example.security.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePwForm {
    private String nickname;

    private String email;

    private String password;

    public ChangePwForm(String nickname, String email, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
