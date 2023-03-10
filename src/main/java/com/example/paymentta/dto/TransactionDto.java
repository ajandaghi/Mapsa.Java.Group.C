package com.example.paymentta.dto;

import com.example.paymentta.entity.Customer;
import com.example.paymentta.entity.TransactionType;
import lombok.Data;

import java.util.Date;
@Data
public class TransactionDto   {

    private String sender;
    private String receiver;
    private Long amount;
    private TransactionType transactionType;
}
