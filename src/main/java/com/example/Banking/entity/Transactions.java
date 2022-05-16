package com.example.Banking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;
    @Column(name = "from_account")
    private int fromAccount;
    @Column(name = "to_account")
    private  int toAccount;
    @Column(name = "amount")
    private float amount;
    @Column(name = "time")
    private  String date;
}
