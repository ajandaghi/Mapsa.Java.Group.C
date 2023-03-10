package com.example.paymentta.entity;

import com.example.paymentta.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerConvertor {
    public Customer convent(CustomerDto customerDto){
        Customer customer = new Customer() ;
        customer.setId(customerDto.getId()) ;
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBalance(customerDto.getBalance());
        customer.setCardNumber(customerDto.getCardNumber());
        customer.setAge(customerDto.getAge());
        customer.setAccountNo(customerDto.getAccountNo());
        return customer ;
    }
}
