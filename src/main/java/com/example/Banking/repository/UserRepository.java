package com.example.Banking.repository;

import com.example.Banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByAccount(int account);
    User findByEmail(String email);

//    @Query(value = "insert into authorities values (?1,?2)",nativeQuery = true)
//    void insertIntoAuthorities(String username,String role);
}
