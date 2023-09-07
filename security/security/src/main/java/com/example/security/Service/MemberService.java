package com.example.security.Service;

import com.example.security.Dto.MemberForm;
import com.example.security.Repository.MemberRepository;
import com.example.security.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    PasswordEncoder passwordEncoder; // DI
    MemberRepository memberRepository;

    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void Register (MemberForm form) {
        // String pw = passwordEncoder.encode(member.setPassword);
        Member member = form.toEntity();

        String encodePW = passwordEncoder.encode(form.getPassword());
        member.setPassword(encodePW);

        memberRepository.save(member);
    }
}
