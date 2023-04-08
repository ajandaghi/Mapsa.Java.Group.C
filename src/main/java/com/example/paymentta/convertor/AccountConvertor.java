package com.example.paymentta.convertor;

import com.example.paymentta.dto.AccountDto;
import com.example.paymentta.dto.CustomerDto;
import com.example.paymentta.entity.Account;
import com.example.paymentta.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface AccountConvertor extends BaseConvertor<AccountDto, Account>{
}
