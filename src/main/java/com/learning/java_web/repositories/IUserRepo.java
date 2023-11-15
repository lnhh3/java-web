package com.learning.java_web.repositories;

import com.learning.java_web.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, String> {
    User findByName(String name);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
