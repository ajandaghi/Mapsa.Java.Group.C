package com.example.paymentta.dto;

import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.entity.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionDto  extends AbstractDto {
    private Date date;

    private Long senderAccId;
    private String sndr;


    private Long receiverAccId;
    private String rcvr;
    private Long amount;

    private String pass2;
    private String cvv2;
    private TransactionType transactionType;


}
