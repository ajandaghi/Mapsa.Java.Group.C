package com.example.paymentta.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Data

public class Pass2Response {

    private Boolean status;
    private String cardNumber;
    private String pass;
    private Date date;
}
