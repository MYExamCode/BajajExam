package com.exam.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.UserDao;
import com.exam.dto.UserDto;
import com.exam.entity.User;

import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

 @Autowired
 private UserDao userRepository;

 public String createUser(UserDto userDto) {
     
     Optional<User> existingUserByPhone = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
     if (existingUserByPhone.isPresent()) {
         return "Phone number already exists";
     }

     Optional<User> existingUserByEmail = userRepository.findByEmailId(userDto.getEmailId());
     if (existingUserByEmail.isPresent()) {
         return "Email ID already exists";
     }

     // Create and save the user
     User user = new User();
     user.setFirstName(userDto.getFirstName());
     user.setLastName(userDto.getLastName());
     user.setPhoneNumber(userDto.getPhoneNumber());
     user.setEmailId(userDto.getEmailId());

     userRepository.save(user);
     return "User created successfully";
 }
}
