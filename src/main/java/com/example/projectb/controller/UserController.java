package com.example.projectb.controller;

import com.example.projectb.model.User;
import com.example.projectb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/third-user")
    public ResponseEntity<User> getThirdUser() {
        try {
            logger.info("Received request to get third user");
            User thirdUser = userService.getThirdUser();
            logger.info("Successfully returning third user: {}", thirdUser);
            return ResponseEntity.ok(thirdUser);
        } catch (RuntimeException e) {
            logger.error("Error occurred while getting third user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

