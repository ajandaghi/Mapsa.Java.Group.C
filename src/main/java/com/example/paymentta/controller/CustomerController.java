package com.example.paymentta.controller;


import com.example.paymentta.convertor.CustomerConvertor;
import com.example.paymentta.dto.CustomerDto;
import com.example.paymentta.entity.Customer;
import com.example.paymentta.exceptions.ServiceException;
import com.example.paymentta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConvertor customerConvertor;


    @PostMapping("/add")
    public void add(@RequestBody CustomerDto customerDto) {

        customerService.insert(customerDto);

    }



    @GetMapping("/{id}")
    public Customer get(@PathVariable("id") Long id) throws ServiceException {

        return customerService.getById(id);
    }
    @PutMapping("/")
    public void update(@RequestBody CustomerDto customer) throws ServiceException {

        customerService.update(customer);
    }


    @GetMapping("/test")
    public void test() throws ServiceException {
        //add(new CustomerDto(null,"reza","rahim",21,"1",190l));
//        update(new CustomerDto(1l,"h1","damirchi",20,191l,"123456"));
//        System.out.println("successfuly");

    }

}
