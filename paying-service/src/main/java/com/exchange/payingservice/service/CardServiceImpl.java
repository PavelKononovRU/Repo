package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
import com.exchange.payingservice.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
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
    public void updateCard(Long id, Card card) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()) {
            Card updateCard = cardOptional.get();
            updateCard.setId(card.getId());
            updateCard.setCSV(card.getCSV());
            updateCard.setNumber(card.getNumber());
            updateCard.setPrincipal(card.getPrincipal());
            updateCard.setUser_id(card.getUser_id());
            cardRepository.save(updateCard);
            System.out.println(updateCard);
        } else {
            throw new UsernameNotFoundException(String.format("Card '%s' not found: ", card));
        }
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
