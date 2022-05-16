package com.example.Banking.repository;

import com.example.Banking.entity.Transactions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void getTransactions() {
        System.out.println(transactionRepository);
        Transactions t = new Transactions();
        t.setDate(LocalDate.now().toString());
        t.setFromAccount(1000);
        t.setToAccount(1001);
        t.setAmount(40);
        transactionRepository.save(t);
        List<Transactions> temp = transactionRepository.getTransactions(t.getFromAccount());
        Assertions.assertThat(temp.size()).isEqualTo(5);
    }
}