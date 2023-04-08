package com.example.paymentta.controller;

import com.example.paymentta.convertor.CardConvertor;
import com.example.paymentta.convertor.CustomerConvertor;
import com.example.paymentta.dto.CardDto;
import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.service.AccountService;
import com.example.paymentta.service.CardService;
import com.example.paymentta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CustomerConvertor customerConvertor;

    @Autowired
    private CardConvertor cardConvertor;

  @Autowired
  private AccountService accountService;

    @PostMapping("/add")
    public void add(@RequestBody CardDto cardDto) throws ServiceException {
        Account account=accountService.getById(cardDto.getAccountId());
        cardDto.setAccount(account);
        cardService.insert(cardConvertor.convertDto(cardDto));

    }

}
