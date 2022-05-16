package com.example.Banking.repository;

import com.example.Banking.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByAccount() {

        User user=new User();
        user.setEmail("abcxyzab@gmail.com");
        user.setPassword("ab");
        user.setEnabled(true);
        user.setAuthority("ROLE_USER");
        user.setLastName("XYZ");
        user.setFirstName("ABC");
        userRepository.save(user);
        User temp=userRepository.findByEmail("abcxyzab@gmail.com");
        System.out.println(temp);
        Assertions.assertThat(user).isEqualTo(temp);
    }

    @Test
    void findByEmail() {
        User user=new User();
        user.setEmail("abcxyzab@gmail.com");
        user.setPassword("ab");
        user.setEnabled(true);
        user.setAuthority("ROLE_USER");
        user.setLastName("XYZ");
        user.setFirstName("ABC");
        userRepository.save(user);
        User temp=userRepository.findByEmail("abcxyzab@gmail.com");
        Assertions.assertThat(user).isEqualTo(temp);
    }
}