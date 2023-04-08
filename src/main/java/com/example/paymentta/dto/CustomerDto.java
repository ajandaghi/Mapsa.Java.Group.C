package com.example.paymentta.dto;


import com.example.paymentta.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CustomerDto extends AbstractDto{

    private String firstName;
    private String lastName;
    private Integer age;
    private String customerNo;

}
