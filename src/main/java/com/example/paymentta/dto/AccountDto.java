package com.example.paymentta.dto;

import com.example.paymentta.entity.AccountType;
import com.example.paymentta.entity.Card;
import com.example.paymentta.entity.Customer;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class AccountDto extends AbstractDto {
    private String accountNumber;
    private AccountType accountType;
    private Customer customer;
    private Long customerId;
    private Long balance;


}
