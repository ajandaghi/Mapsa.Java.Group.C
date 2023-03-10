package com.example.paymentta.service;

import com.example.paymentta.entity.Transaction;
import com.example.paymentta.repository.TransactionRepository;
import com.example.paymentta.service.notifications.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class CardService extends TransactionService {

}
