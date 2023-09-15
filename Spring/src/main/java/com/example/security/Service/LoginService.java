package com.example.security.Service;

import com.example.security.Repository.MemberRepository;
import com.example.security.Dto.LoginForm;
import com.example.security.domain.Member;
import com.example.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
    MemberRepository memberRepository;

    PasswordEncoder passwordEncoder; // DI

    @Autowired
    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Value("${jwt.secret}")
    String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public String login(LoginForm loginForm) {

        // 1. Id가 틀린 경우
        Member member = memberRepository.findByEmail(loginForm.getId())
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        // 2. Pw가 틀린 경우
        String userPasswordFromDB = member.getPassword();
        if (!passwordEncoder.matches(userPasswordFromDB, loginForm.getPw() )) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 로그인이 정상적으로 되었을 경우!
        long userid = member.getId(); // id값을 기준으로 userid가져오기!

        return JwtUtil.creatJwt(userid, secretKey, expiredMs);
    }

    public String KakaoLogin (String email) {

        // 1. Id가 틀린 경우
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        // 3. 로그인이 정상적으로 되었을 경우!
        long userid = member.getId(); // id값을 기준으로 userid가져오기!

        return JwtUtil.creatJwt(userid, secretKey, expiredMs);
    }
}
