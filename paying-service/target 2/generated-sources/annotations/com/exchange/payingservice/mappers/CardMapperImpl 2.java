package com.exchange.payingservice.mappers;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-25T16:42:17+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDTO toDTO(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();

        cardDTO.setId( card.getId() );
        cardDTO.setNumber( card.getNumber() );
        cardDTO.setPrincipal( card.getPrincipal() );
        cardDTO.setCSV( card.getCSV() );
        cardDTO.setUser_id( card.getUser_id() );

        return cardDTO;
    }

    @Override
    public Card toEntity(CardDTO cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        Card card = new Card();

        card.setId( cardDTO.getId() );
        card.setNumber( cardDTO.getNumber() );
        card.setPrincipal( cardDTO.getPrincipal() );
        card.setCSV( cardDTO.getCSV() );
        card.setUser_id( cardDTO.getUser_id() );

        return card;
    }

    @Override
    public void updateCardFromDto(CardDTO cardDTO, Card card) {
        if ( cardDTO == null ) {
            return;
        }

        if ( cardDTO.getId() != null ) {
            card.setId( cardDTO.getId() );
        }
        if ( cardDTO.getNumber() != null ) {
            card.setNumber( cardDTO.getNumber() );
        }
        if ( cardDTO.getPrincipal() != null ) {
            card.setPrincipal( cardDTO.getPrincipal() );
        }
        if ( cardDTO.getCSV() != null ) {
            card.setCSV( cardDTO.getCSV() );
        }
        if ( cardDTO.getUser_id() != null ) {
            card.setUser_id( cardDTO.getUser_id() );
        }
    }
}
