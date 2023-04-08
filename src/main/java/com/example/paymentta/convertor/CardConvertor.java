package com.example.paymentta.convertor;

import com.example.paymentta.dto.CardDto;
import com.example.paymentta.entity.Card;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
abstract public class CardConvertor implements BaseConvertor<CardDto, Card> {
}
