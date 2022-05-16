package com.example.Banking.services;

import com.example.Banking.entity.User;
import com.example.Banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void saveUser(User user){
        user.setEnabled(Boolean.TRUE);
        user.setPassword(user.getPassword());
        user.setAuthority("ROLE_USER");
        userRepository.save(user);
    }
    public User findUserByEmail(String email){
       return userRepository.findByEmail(email);
    }
    public User findUserByAccountNo(int accountNo){
        return userRepository.findByAccount(accountNo);
    }
}
