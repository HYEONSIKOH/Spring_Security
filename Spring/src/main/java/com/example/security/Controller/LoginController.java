package com.example.security.Controller;

import com.example.security.Service.LoginService;
import com.example.security.Dto.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public String login(@RequestBody LoginForm loginForm){
        System.out.println("loginForm = " + loginForm);

        return loginService.login(loginForm);
    }
}
