package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
import com.exchange.payingservice.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public CardDTO createCard(Card card) {
        System.out.println(card);
        cardRepository.saveAndFlush(card);
        return CardMapper.INSTANCE.toDTO(cardRepository.getCardByNumber(card.getNumber()));
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
