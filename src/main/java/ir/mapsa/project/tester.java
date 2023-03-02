package ir.mapsa.project;

import ir.mapsa.project.entity.Customer;
import ir.mapsa.project.entity.Transaction;
import ir.mapsa.project.repository.CustomerRepository;
import ir.mapsa.project.service.TransactionService;

import java.sql.Date;
import java.time.LocalDate;

public class tester {
    public static void main(String[] args) throws Exception {
        Customer customer1=new Customer();
        customer1.setFirstName("Ali");
        customer1.setLastName("Alavi");
        customer1.setCardNo("1234 1234 1234 1234");
        customer1.setAge(22);
        customer1.setBalance(40000L);
        customer1.setEmail("ali.jandaghi.6161@gmail.com");
        customer1.setPhone("000000000");

        CustomerRepository customerRepository=new CustomerRepository();
        customerRepository.add(customer1);

        Customer customer2=new Customer();
        customer2.setFirstName("Reza");
        customer2.setLastName("Razavi");
        customer2.setCardNo("4321 4321 4321 4321");
        customer2.setAge(34);
        customer2.setBalance(50000L);
        customer2.setEmail("rayanelectronictech@gmail.com");
        customer2.setPhone("000000000");

        customerRepository.add(customer2);

        Transaction transaction=new Transaction();
        transaction.setAmount(12000L);
        transaction.setSenderCardNumber("1234 1234 1234 1234");
        transaction.setRecieverCardNumber("4321 4321 4321 4321");
        transaction.setDate(Date.valueOf(LocalDate.now()));

        TransactionService transactionService=new TransactionService();
        transactionService.resolveTransaction(transaction);





    }
}
