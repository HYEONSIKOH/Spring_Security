package com.example.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "null", timeToLive = 180) // options: timeToLive = 10
@NoArgsConstructor
@Getter
@Setter
public class EmailAuthForm {

    @Id
    private String email;

    private String code;

    public EmailAuthForm(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
