package com.example.security.EmailAuth;

import com.example.security.domain.EmailAuthForm;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRepository extends CrudRepository<EmailAuthForm, String> {
}
