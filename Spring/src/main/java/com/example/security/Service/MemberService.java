package com.example.security.Service;

import com.example.security.Dto.MemberForm;
import com.example.security.Repository.MemberRepository;
import com.example.security.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:Spring/src/main/java/com/example/security/Service/MemberService.java
=======
import java.util.List;
>>>>>>> main:security/security/src/main/java/com/example/security/Service/MemberService.java
import java.util.Optional;

@Service
public class MemberService {

    PasswordEncoder passwordEncoder; // DI
    MemberRepository memberRepository;

<<<<<<< HEAD:Spring/src/main/java/com/example/security/Service/MemberService.java
=======
    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

>>>>>>> main:security/security/src/main/java/com/example/security/Service/MemberService.java
    // 회원가입
    public void Register (MemberForm form) {
        // String pw = passwordEncoder.encode(member.setPassword);
        Member member = form.toEntity();

        String encodePW = passwordEncoder.encode(form.getPassword());
        member.setPassword(encodePW);

        memberRepository.save(member);
    }

    // 닉네임 + email로 회원이 있는지 없는지 확인
    public boolean checkIfUserExists (String nickname, String email) {
        Optional<Member> optionalMember = memberRepository.findByNicknameAndEmail(nickname, email);

        if(optionalMember.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    // 비밀번호 변경 (회원이 있을 경우만)
    public void changePassword (String nickname, String email, String pw) {
        Optional<Member> optionalMember = memberRepository.findByNicknameAndEmail(nickname, email);
        Member member = optionalMember.get();

        String encodePW = passwordEncoder.encode(pw);
        member.setPassword(encodePW);

        memberRepository.save(member);
    }
}
