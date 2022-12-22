package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Card;
import com.exchangeinformat.userprofile.repository.CardRepository;
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
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void createCard(Card card) {
        cardRepository.save(card);
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
}
