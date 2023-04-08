package com.example.paymentta.service;


import com.example.paymentta.convertor.CustomerConvertor;
import com.example.paymentta.dto.CustomerDto;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.entity.TransactionType;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerService  {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConvertor customerConvertor ;



    public void insert(CustomerDto customerDto) {

        /*todo: add validation*/

        customerRepository.save(customerConvertor.convertDto(customerDto));

    }

    public Customer getById(Long id) throws ServiceException {

        Optional<Customer> customer = customerRepository.findById(id);

        try {
            return customer.orElseThrow();
        } catch (Exception e) {

            throw new ServiceException(e.getMessage(), "user not Found");
        }


    }





    public void update(CustomerDto customerDto) throws ServiceException {
        Customer customer =customerRepository.findById(customerDto.getId())
                .orElseThrow(()->new ServiceException("customer not found"));
        //Customer customer = customerConvertor.convent(customerDto) ;
        change(customer,customerDto);
        customerRepository.save(customer) ;

    }
    private void change(Customer customer, CustomerDto customerDto){

       // if(customerDto.getCards()!= null) customer.setCards(customerDto.getCards());

        if(customerDto.getAge()!= null) customer.setAge(customerDto.getAge());
        if(customerDto.getCustomerNo()!= null) customer.setCustomerNo(customerDto.getCustomerNo());
        if(customerDto.getFirstName()!= null) customer.setFirstName(customerDto.getFirstName());
        if(customerDto.getLastName()!= null) customer.setLastName(customerDto.getLastName());
    }


}
