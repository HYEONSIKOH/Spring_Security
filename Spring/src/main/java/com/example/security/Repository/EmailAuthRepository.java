package com.example.security.Repository;

import com.example.security.Dto.EmailAuthForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthRepository extends CrudRepository<EmailAuthForm, String> {
}
