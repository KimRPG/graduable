package com.software.graduable.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJPA extends JpaRepository<User, Long> {
    User findByGoogleId(String googleId);
    
    @Modifying
    @Query("UPDATE User u SET u.userSemester = :semester WHERE u.googleId = :googleId")
    void updateUserSemester(@Param("googleId") String googleId, @Param("semester") int semester);
}
