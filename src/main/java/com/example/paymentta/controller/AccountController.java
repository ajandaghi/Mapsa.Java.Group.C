package com.example.paymentta.controller;

import com.example.paymentta.convertor.AccountConvertor;
import com.example.paymentta.convertor.CardConvertor;
import com.example.paymentta.dto.AccountDto;
import com.example.paymentta.dto.CardDto;
import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.service.AccountService;
import com.example.paymentta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountConvertor accountConvertor;

    @PostMapping("/add")
    public void add(@RequestBody AccountDto accountDto) throws ServiceException {
        Customer customer=customerService.getById(accountDto.getCustomerId());
        accountDto.setCustomer(customer);
        accountService.insert(accountConvertor.convertDto(accountDto));

    }
}
