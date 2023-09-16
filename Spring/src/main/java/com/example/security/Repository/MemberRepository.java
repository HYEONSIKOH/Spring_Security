package com.example.security.Repository;

import com.example.security.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNicknameAndEmail(String nickname, String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByKakaoid(long kakaoid);
}
