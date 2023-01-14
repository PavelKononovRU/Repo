package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
import com.exchange.payingservice.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    @Transactional
    public CardDTO createCard(CardDTO card) {
        if (getAllCard().stream().map(Card::getNumber).anyMatch(s->s.equals(card.getNumber()))) {
            throw new PaymentException("Данный номер карты уже существует");
        }
        cardRepository.saveAndFlush(CardMapper.INSTANCE.toEntity(card));
        return card;
    }

    @Override
    @Transactional
    public void updateCard(Long id, CardDTO cardDTO) {
        Card cardUp = cardRepository.getReferenceById(id);
        cardMapper.updateCardFromDto(cardDTO, cardUp);
        cardRepository.save(cardUp);
    }

    @Override
    @Transactional
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> getAllCard() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

}
