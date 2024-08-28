package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.dto.UserDto;
import com.exam.service.UserService;

@RestController
@RequestMapping("/automation-campus") // Adjusted base path
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create/user") // Adjusted the endpoint path
    public ResponseEntity<String> createUser(@RequestHeader(value = "roll-number", required = false) String rollNumber, 
                                             @RequestBody UserDto userDto) {
        if (rollNumber == null || rollNumber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Roll number is required");
        }

        String result = userService.createUser(userDto);
        if ("Phone number already exists".equals(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        if ("Email ID already exists".equals(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.ok(result);
    }
}
