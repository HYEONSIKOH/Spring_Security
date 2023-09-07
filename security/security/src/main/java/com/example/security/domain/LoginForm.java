package com.example.security.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class LoginForm {

    private String id;

    private String pw;

    public String getId() {
        // ID 값을 반환하는 코드 작성
        return id;
    }

    public String getPw() {
        return pw;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public void setPw(String Pw) {
        this.pw = Pw;
    }
}
