package com.example.paymentta.service;

import com.example.paymentta.dto.TransactionDto;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.exceptions.ServiceException;
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
    private CustomerService customerService;
    @Autowired
    private TransactionRepository transactionRepository;



    @Autowired
    private NotificationSender notificationSender;


    @Transactional(rollbackFor = ServiceException.class)
    public void resolveTransaction(TransactionDto trx) throws ServiceException {

        Customer sender = customerService.withdraw(trx.getSender(), trx.getAmount(),trx.getTransactionType());


        if (sender == null) {
            throw new ServiceException(" your balance is not enough");
        }

        Customer receiver = customerService.deposit(trx.getReceiver(), trx.getAmount(), trx.getTransactionType());

        if (receiver == null) {
            throw new ServiceException("receiver card number is not valid ");
        }

        com.example.paymentta.entity.Transaction trxEntity= new com.example.paymentta.entity.Transaction();
        trxEntity.setAmount(trx.getAmount());
        trxEntity.setReceiver(receiver);
        trxEntity.setSender(sender);
        trxEntity.setDate(new Date());
        trxEntity.setTransactionType(trx.getTransactionType());

        transactionRepository.save(trxEntity);

        notificationSender.send(NotificationType.EMAIL, new NotificationText("kasr ", sender.getCardNumber(), trx.getAmount(), trxEntity.getDate()));
        notificationSender.send(NotificationType.SMS, new NotificationText("plus", receiver.getCardNumber(), trx.getAmount(), trxEntity.getDate()));



    }
}
