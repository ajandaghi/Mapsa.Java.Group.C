package com.example.paymentta.service;

import com.example.paymentta.convertor.CardConvertor;
import com.example.paymentta.dto.CardDto;
import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Card;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.entity.Transaction;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.repository.CardRepository;
import com.example.paymentta.repository.TransactionRepository;
import com.example.paymentta.service.notifications.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class CardService  {

    @Autowired
    public CardRepository cardRepository;

    @Autowired
    private CardConvertor cardConvertor;

    public Card findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    public Long accountBalance(String cardNumber) throws ServiceException {

       Account acount= cardRepository.findByCardNumber(cardNumber).getAccount();
        if (acount != null){

            return acount.getBalance();
        }else {

            throw new ServiceException("account_not_found");
        }

    }

    public Card getById(Long id) throws ServiceException {

        Optional<Card> card = cardRepository.findById(id);

        try {
            return card.orElseThrow();
        } catch (Exception e) {

            throw new ServiceException(e.getMessage(), "card not Found");
        }


    }

    public void insert(Card card){

        cardRepository.save(card);
    }

}
