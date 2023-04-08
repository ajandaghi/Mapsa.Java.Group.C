package com.example.paymentta.dto;

import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Customer;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CardDto extends AbstractDto {
    private Long id;
    private String cardNumber;
    private String cvv2;
    private Date expDate;
    private Account account;
    private Long accountId;
}
