package com.gom.My_Library.repositories;

import com.gom.My_Library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
