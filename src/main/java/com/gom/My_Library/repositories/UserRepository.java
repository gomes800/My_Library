package com.gom.My_Library.repositories;

import com.gom.My_Library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
