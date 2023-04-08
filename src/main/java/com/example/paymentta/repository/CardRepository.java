package com.example.paymentta.repository;

import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Card;
import com.example.paymentta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
}
