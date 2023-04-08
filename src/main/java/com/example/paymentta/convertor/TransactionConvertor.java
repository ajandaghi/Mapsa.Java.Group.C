package com.example.paymentta.convertor;

import com.example.paymentta.dto.TransactionDto;
import com.example.paymentta.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TransactionConvertor extends BaseConvertor<TransactionDto, Transaction>  {
}
