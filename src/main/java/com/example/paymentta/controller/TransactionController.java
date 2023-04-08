package com.example.paymentta.controller;

import com.example.paymentta.convertor.AccountConvertor;
import com.example.paymentta.convertor.TransactionConvertor;
import com.example.paymentta.dto.CustomerDto;
import com.example.paymentta.dto.TransactionDto;
import com.example.paymentta.entity.*;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.service.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

   @Autowired
   private  CardService cardService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PasswordService passwordService;

   @Autowired
   private TransactionConvertor transactionConvertor;

   @Autowired
   private AccountConvertor accountConvertor;

    @PostMapping("/cardToCard")
    public void transaction(@RequestBody TransactionDto transactionDto) throws ServiceException {

        transactionDto.setReceiverAccId(cardService.findByCardNumber(transactionDto.getRcvr()).getId());
        transactionDto.setSenderAccId(cardService.findByCardNumber(transactionDto.getSndr()).getId());

        if(passwordService.password2Validator(transactionDto.getSndr(),transactionDto.getPass2()) && cardService.findByCardNumber(transactionDto.getSndr()).getCvv2().equals(transactionDto.getCvv2())) {

                transactionService.resolveTransaction(transactionConvertor.convertDto(transactionDto),transactionDto.getSndr(),transactionDto.getRcvr() );


        }

    }

    @PostMapping("getPass2")
    public Pass2Response getPass2(@RequestBody Pass2Response cardNumber){

       return passwordService.generator(cardNumber.getCardNumber());

    }





    @PostMapping("/account")
    public void transfer(@RequestBody TransactionDto trx ) throws ServiceException {
        trx.setReceiverAccId(accountService.findByAccountNumber(trx.getRcvr()).getId());
        trx.setSenderAccId(accountService.findByAccountNumber(trx.getSndr()).getId());

        transactionService.resolveTransaction(transactionConvertor.convertDto(trx),trx.getSndr(),trx.getRcvr());


    }



    @PostMapping("/get")
    public void getTrx(@RequestBody TransactionDto trx) {

//        transactionService.getTransactions(trx.getReceiver().getCardNumber(),
//                trx.getSender().getCardNumber(),
//                trx.getDate(),
//                trx.getDate());
    }


}
