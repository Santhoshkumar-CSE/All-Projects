package com.authService.june.authServiceJune.repository;

import com.authService.june.authServiceJune.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);
    User findByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
