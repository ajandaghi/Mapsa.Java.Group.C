package com.example.paymentta.service;

import com.example.paymentta.convertor.CustomerConvertor;
import com.example.paymentta.dto.CustomerDto;
import com.example.paymentta.dto.TransactionDto;
import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.entity.Transaction;
import com.example.paymentta.entity.TransactionType;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.repository.CardRepository;
import com.example.paymentta.repository.TransactionRepository;
import com.example.paymentta.service.notifications.NotificationSender;
import com.example.paymentta.service.notifications.NotificationText;
import com.example.paymentta.service.notifications.NotificationType;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationSender notificationSender;

    @Autowired
    private CardRepository cardService;


    @Autowired
    private CustomerConvertor customerConvertor;

    @Autowired
    private AccountService accountService;

    @Transactional(rollbackFor = ServiceException.class)
    public void resolveTransaction(Transaction trx,String sndr, String rcvr) throws ServiceException {

        Customer sender = withdraw(sndr, trx.getAmount(),trx.getTransactionType());


        if (sender == null) {
            throw new ServiceException(" your balance is not enough");
        }

        Customer receiver = deposit(rcvr, trx.getAmount(), trx.getTransactionType());

        if (receiver == null) {
            throw new ServiceException("receiver card number is not valid ");
        }

        com.example.paymentta.entity.Transaction trxEntity= new com.example.paymentta.entity.Transaction();

        trxEntity.setAmount(trx.getAmount());
        if(trx.getTransactionType().equals(TransactionType.CARD_TO_CARD)) {
            trxEntity.setReceiverAccId(cardService.findByCardNumber(rcvr).getAccount().getId());
            trxEntity.setSenderAccId(cardService.findByCardNumber(sndr).getAccount().getId());
        }else{
            trxEntity.setReceiverAccId(accountService.findByAccountNumber(rcvr).getId());
            trxEntity.setSenderAccId(accountService.findByAccountNumber(sndr).getId());
        }
        trxEntity.setDate(new Date());
        trxEntity.setTransactionType(trx.getTransactionType());

        transactionRepository.save(trxEntity);

        notificationSender.send(NotificationType.EMAIL, new NotificationText("kasr ",sndr, trx.getAmount(), trxEntity.getDate()));
        notificationSender.send(NotificationType.SMS, new NotificationText("plus", rcvr, trx.getAmount(), trxEntity.getDate()));



    }


    public Customer withdraw(String sender, Long amount, TransactionType transactionType) throws ServiceException {
        Customer c=null;
        if(transactionType.equals(TransactionType.CARD_TO_CARD)) {
            c = cardService.findByCardNumber(sender).getAccount().getCustomer();
            if(c==null){
                throw new ServiceException("user_not_found");

            }
        } else{
            c = cardService.findByCardNumber(sender).getAccount().getCustomer();
            if(c==null){
                throw new ServiceException("user_not_found");

            }

        }


        if (c!= null && amount < cardService.findByCardNumber(sender).getAccount().getBalance()) {

            Account account=cardService.findByCardNumber(sender).getAccount();
            account.setBalance(account.getBalance()-amount);
            accountService.insert(account);

            return c;

        }
        return null;
    }

    public Customer deposit(String receiver, Long amount, TransactionType transactionType) throws ServiceException {

        Customer c=null;
        if(transactionType.equals(TransactionType.CARD_TO_CARD)) {
            c = cardService.findByCardNumber(receiver).getAccount().getCustomer();
            if(c==null){
                throw new ServiceException("user_not_found");

            }
        } else{
            c = cardService.findByCardNumber(receiver).getAccount().getCustomer();
            if(c==null){
                throw new ServiceException("user_not_found");

            }

        }
        if (c != null) {

            Account account=cardService.findByCardNumber(receiver).getAccount();
            account.setBalance(account.getBalance()+amount);
            accountService.insert(account);
            return c;

        }
        return null;
    }
}
