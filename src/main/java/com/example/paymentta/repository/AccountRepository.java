package com.example.paymentta.repository;

import com.example.paymentta.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long>
{
    Account findAccountByAccountNumber(String accountNumber);
}
