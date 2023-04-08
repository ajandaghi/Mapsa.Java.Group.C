package com.example.paymentta.service;

import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Card;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.repository.AccountRepository;
import com.example.paymentta.repository.CardRepository;
import com.example.paymentta.repository.TransactionRepository;
import com.example.paymentta.service.notifications.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AccountService  {
@Autowired
AccountRepository accountRepository;

    public Account getById(Long id) throws ServiceException {

        Optional<Account> account = accountRepository.findById(id);

        try {
            return account.orElseThrow();
        } catch (Exception e) {

            throw new ServiceException(e.getMessage(), "account not Found");
        }


    }

    public void insert(Account account){

        accountRepository.save(account);
    }

    public Account findByAccountNumber(String accountNumber){
       return accountRepository.findAccountByAccountNumber(accountNumber);
    }
}
