package com.software.graduable.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, Long> {
    User findByGoogleId(String googleId);
}
