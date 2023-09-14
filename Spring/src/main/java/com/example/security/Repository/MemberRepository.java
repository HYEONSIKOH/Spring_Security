package com.example.security.Repository;

import com.example.security.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD:Spring/src/main/java/com/example/security/Repository/MemberRepository.java
import org.springframework.stereotype.Repository;
=======

import java.util.List;
import java.util.Optional;
>>>>>>> main:security/security/src/main/java/com/example/security/Repository/MemberRepository.java

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNicknameAndEmail(String nickname, String email);

<<<<<<< HEAD:Spring/src/main/java/com/example/security/Repository/MemberRepository.java
    Optional<Member> findByEmail(String email);
=======
>>>>>>> main:security/security/src/main/java/com/example/security/Repository/MemberRepository.java
}
