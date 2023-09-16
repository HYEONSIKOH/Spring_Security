package com.example.security.Controller;

import com.example.security.Dto.ChangePwForm;
import com.example.security.Dto.CheckPwForm;
import com.example.security.Dto.KakaoDataForm;
import com.example.security.Dto.MemberForm;
import com.example.security.Kakao.KakaoService;
import com.example.security.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 회원가입 API
    @PostMapping("register")
    public ResponseEntity<String> register (@RequestBody MemberForm memberForm) {
        System.out.println(memberForm.getHeight());
        memberService.Register(memberForm);

        return ResponseEntity.ok().body("굿!");
    }

    // 비밀번호 변경 시, 유저 확인
    @PostMapping ("checkuser")
    public ResponseEntity<String> checkUser (@RequestBody CheckPwForm checkPwForm) {

        if(memberService.checkIfUserExists(checkPwForm.getNickname(), checkPwForm.getEmail())) {
            return ResponseEntity.ok().body("sucess");
        } else {
            return ResponseEntity.status(201).body("No user");
        }
    }

    // 비밀번호 변경 API
    @PostMapping("changepw")
    public ResponseEntity<String> changePassword (@RequestBody ChangePwForm changePwForm) {
        memberService.changePassword(changePwForm.getNickname(), changePwForm.getEmail(), changePwForm.getPassword());

        return ResponseEntity.ok().body("Sucess of change password");
    }
}
