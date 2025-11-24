package com.example.projectb.service;

import com.example.projectb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RestTemplate restTemplate;
    private static final String PROJECTA_API_URL = "http://localhost:8080/api/users";

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    public User getThirdUser() {
        try {
            // Call projecta API to get user list
            logger.info("Calling projecta API: {}", PROJECTA_API_URL);
            ResponseEntity<List<User>> response = restTemplate.exchange(
                    PROJECTA_API_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>() {}
            );
            
            List<User> users = response.getBody();
            
            // Return the third element (index 2)
            if (users != null && users.size() > 2) {
                User thirdUser = users.get(4);
                logger.info("Successfully retrieved third user: {}", thirdUser);
                return thirdUser;
            } else {
                String errorMsg = "User list does not contain a third element. List size: " + (users != null ? users.size() : 0);
                logger.error(errorMsg);
                throw new RuntimeException(errorMsg);
            }
        } catch (RuntimeException e) {
            logger.error("Failed to get third user from projecta API: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error calling projecta API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to call projecta API: " + e.getMessage(), e);
        }
    }
}

