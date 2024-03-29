package com.example.paymentta.repository;

import com.example.paymentta.entity.Card;
import com.example.paymentta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



}
