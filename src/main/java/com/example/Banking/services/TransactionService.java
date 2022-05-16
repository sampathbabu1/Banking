package com.example.Banking.services;

import com.example.Banking.entity.Transactions;
import com.example.Banking.entity.User;
import com.example.Banking.repository.TransactionRepository;
import com.example.Banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    public void doTransaction(Transactions transaction){
        User user=userRepository.findByAccount(transaction.getFromAccount());
        user.setBalance(user.getBalance()-transaction.getAmount());
        userRepository.save(user);
        User user1=userRepository.findByAccount(transaction.getToAccount());
        user1.setBalance(user1.getBalance()+transaction.getAmount());
        transaction.setDate(LocalDateTime.now().toString());
        userRepository.save(user1);
        transactionRepository.save(transaction);
    }
    public List<Transactions> listOfTransactions(int uid){
        return transactionRepository.getTransactions(uid);
    }
}
