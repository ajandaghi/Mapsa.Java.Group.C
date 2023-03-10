package com.example.paymentta.controller;

import com.example.paymentta.dto.TransactionDto;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {



    @Autowired
    @Qualifier("accountService")
    private TransactionService accountService;

    @Autowired
    @Qualifier("cardService")
    private TransactionService cardService;


    @PostMapping("/cardToCard")
    public void transaction(@RequestBody  TransactionDto trx ) throws ServiceException {

        accountService.resolveTransaction(trx);


    }

    @PostMapping("/account")
    public void transfer(@RequestBody TransactionDto trx ) throws ServiceException {

        cardService.resolveTransaction(trx);


    }



    @PostMapping("/get")
    public void getTrx(@RequestBody TransactionDto trx) {

//        transactionService.getTransactions(trx.getReceiver().getCardNumber(),
//                trx.getSender().getCardNumber(),
//                trx.getDate(),
//                trx.getDate());
    }


}
