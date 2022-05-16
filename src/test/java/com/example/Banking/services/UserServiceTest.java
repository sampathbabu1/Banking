package com.example.Banking.services;

import com.example.Banking.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        User user = new User();
        user.setFirstName("ABC");
        user.setLastName("BC");
        user.setPassword("12");
        user.setEmail("ABCBC@gmail.com");
        userService.saveUser(user);
        User result=userService.findUserByEmail("ABCBC@gmail.com");
        Assertions.assertThat(result.getEnabled()).isEqualTo(true);
    }

    @Test
    void findUserByEmail() {
        Assertions.assertThat(userService.findUserByEmail("ABCBC@gmail.com")).isNotNull();
    }
}