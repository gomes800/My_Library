package com.gom.My_Library.repositories;

import com.gom.My_Library.models.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

}
