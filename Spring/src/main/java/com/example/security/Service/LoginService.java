package com.example.security.Service;

import com.example.security.Dto.KakaoDataForm;
import com.example.security.Repository.MemberRepository;
import com.example.security.Dto.LoginForm;
import com.example.security.domain.Member;
import com.example.security.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder; // DI

    @Value("${jwt.secret}")
    String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public String login(LoginForm loginForm) {

        // 1. Id가 틀린 경우
        Member member = memberRepository.findByEmail(loginForm.getId())
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        // 2. Pw가 틀린 경우
        String userPasswordFromDB = member.getPassword();
        if (!passwordEncoder.matches(loginForm.getPw() ,userPasswordFromDB) ) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 로그인이 정상적으로 되었을 경우!
        long userid = member.getId(); // id값을 기준으로 userid가져오기!

        return JwtUtil.creatJwt(userid, secretKey, expiredMs);
    }

    public String KakaoLogin (KakaoDataForm kakaoDataForm) {

        // 1. 카카오로 연동하지 않은 경우
        Member member = memberRepository.findByKakaoid(kakaoDataForm.getId())
                .orElseThrow(() -> new RuntimeException("카카오로 연동된 계정이 없습니다."));

        // 3. 로그인이 정상적으로 되었을 경우!
        long userid = member.getId(); // id값을 기준으로 userid가져오기!

        return JwtUtil.creatJwt(userid, secretKey, expiredMs);
    }
}
