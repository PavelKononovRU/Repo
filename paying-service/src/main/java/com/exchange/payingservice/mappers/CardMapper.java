package com.exchange.payingservice.mappers;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDTO toDTO(Card card);
    Card toEntity(CardDTO cardDTO);

}
