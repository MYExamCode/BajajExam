package com.exam.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
// Optional<User> findByPhoneNumber(Long phoneNumber);
 Optional<User> findByEmailId(String emailId);
Optional<User> findByPhoneNumber(Long phoneNumber);
}
