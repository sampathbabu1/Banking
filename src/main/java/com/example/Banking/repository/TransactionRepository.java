package com.example.Banking.repository;

import com.example.Banking.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
    @Query(value="select * from transactions where from_account = ?1 or to_account = ?1",nativeQuery = true)
    List<Transactions> getTransactions(int fromAccount);
}
