package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.StatusCards;
import com.exchange.payingservice.repository.CardRepository;
import com.exchange.payingservice.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    @Transactional
    public StatusCards createCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new StatusCards("Карта сохранена",
                "Управляйте картами в платежной информации",
                cardRepository.getCardByNumber(card.getNumber()).getId());
    }

    @Override
    @Transactional
    public void updateCard(Card card) {
        cardRepository.save(card);
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
