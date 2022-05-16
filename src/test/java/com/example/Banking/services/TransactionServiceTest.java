package com.example.Banking.services;

import com.example.Banking.entity.Transactions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @Test
    void doTransaction() {
        Transactions transaction=new Transactions();
        transaction.setFromAccount(1000);
        transaction.setToAccount(1001);
        transaction.setAmount(20);
        transaction.setDate(LocalDate.now().toString());
        transactionService.doTransaction(transaction);
        List<Transactions>transactions=transactionService.listOfTransactions(1000);
        Assertions.assertThat(transactions).hasSizeGreaterThan(0);
    }

    @Test
    void listOfTransactions() {
        List<Transactions> transactions=transactionService.listOfTransactions(1001);
        Assertions.assertThat(transactions).hasSizeGreaterThan(0);
    }
}