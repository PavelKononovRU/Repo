package com.exchange.payingservice.mappers;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDTO toDTO(Card card);
    Card toEntity(CardDTO cardDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCardFromDto(CardDTO cardDTO, @MappingTarget Card card);

}
