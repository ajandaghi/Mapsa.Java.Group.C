package com.example.paymentta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PasswordService {

    private final Object OBJ=new Object();
    @Autowired
    Pass2Response pass2Response;

    private Map<String, PassDate> pass2 = new HashMap<>();
    public Pass2Response generator(String cardNo) {
        synchronized (OBJ) {
            if (pass2.get(cardNo) == null || (new Date()).getTime() - (pass2.get(cardNo).getDate().getTime()) > 120000) {

                int number = (int) Math.round(899999*Math.random()+100000);

                // this will convert any number sequence into 6 character.
              pass2.put(cardNo,new PassDate(String.valueOf(number),new Date()));
                pass2Response.setPass(pass2.get(cardNo).getPass());
                pass2Response.setDate(pass2.get(cardNo).getDate());
                pass2Response.setCardNumber(cardNo);
                pass2Response.setStatus(true);
                return pass2Response;



            }
            pass2Response.setStatus(false);
            return pass2Response;
        }
    }

    public boolean password2Validator(String cardNo, String pass) {
        return ((new Date()).getTime()-pass2.get(cardNo).getDate().getTime()<=120000) && pass2.get(cardNo).getPass().equals(pass);

    }
}

