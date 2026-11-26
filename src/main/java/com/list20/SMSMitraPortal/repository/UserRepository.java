package com.list20.SMSMitraPortal.repository;


import java.util.Optional;

import com.list20.SMSMitraPortal.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}

