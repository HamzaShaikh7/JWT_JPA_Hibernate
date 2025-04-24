package com.jwt2.JWT_Hiber.repository;

import com.jwt2.JWT_Hiber.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

}
