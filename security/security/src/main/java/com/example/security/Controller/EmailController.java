package com.example.security.Controller;

import com.example.security.Email.MailService;
import com.example.security.EmailAuth.EmailAuthService;
import com.example.security.domain.EmailAuthForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Slf4j
public class EmailController {
    @Autowired
    // 이메일 인증
    private MailService mailService;

    @Autowired
    private EmailAuthService serivce;

    @PostMapping("mailConfirm")
    public String mailConfirm(@RequestParam String email) throws Exception {
        String code = mailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        //String code = "12345";

        serivce.saveDataWithExpiration(email, code, 180);

        return code;
    }

    @PostMapping("mailAuth")
    public ResponseEntity<String> mailAuth(@RequestBody EmailAuthForm emailAuthForm) throws Exception {

        try {
            if(serivce.getData(emailAuthForm.getEmail()).equals(emailAuthForm.getCode())) {
                return ResponseEntity.ok().body("sucess!");
            } else {
                return ResponseEntity.status(201).body("false");
            }
        } catch (NullPointerException e) {
            System.out.println("인증 코드 시간이 만료되었습니다.");
            return ResponseEntity.status(201).body("인증 코드 시간이 만료되었습니다.");
        }
    }
}
