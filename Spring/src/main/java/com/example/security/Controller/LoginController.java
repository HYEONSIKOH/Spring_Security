package com.example.security.Controller;

import com.example.security.Dto.KakaoDataForm;
import com.example.security.Kakao.KakaoService;
import com.example.security.Service.LoginService;
import com.example.security.Dto.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final KakaoService kakaoService;

    @PostMapping("/normal")
    public String login(@RequestBody LoginForm loginForm){
        System.out.println("loginForm = " + loginForm);

        return loginService.login(loginForm);
    }

    @GetMapping("/kakao")
    public String KakaoLogin (@RequestParam String code) {
        // System.out.println("code = " + code);

        String token = kakaoService.getKaKaoAccessToken(code);
        KakaoDataForm res = kakaoService.createKakaoUser(token);

        return loginService.KakaoLogin(res);
    }
}
