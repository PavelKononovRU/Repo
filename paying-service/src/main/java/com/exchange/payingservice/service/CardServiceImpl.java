package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
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
    public void createCard(CardDTO card) {
        if (cardRepository.getCardByNumber(card.getNumber()) != null) {
            throw new PaymentException("Данный номер карты уже существует");
        }
        cardRepository.saveAndFlush(CardMapper.INSTANCE.toEntity(card));
    }

    @Override
    @Transactional
    public void updateCard(Long id, CardDTO cardDTO) {
        Card cardUp = cardRepository.getReferenceById(id);
        cardMapper.updateCardFromDto(cardDTO, cardUp);
        cardRepository.save(cardUp);
    }
/*
* Вызов getCardById для обработки возможного NullPointerException
* */
    @Override
    @Transactional
    public void deleteCard(Long id) {
        getCardById(id);
        cardRepository.deleteById(id);
    }

    @Override
    public List<CardDTO> getAllCard() {
        return cardRepository.findAll().stream().map(cardMapper::toDTO).toList();
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() ->
            new PaymentException("Карта с номером " + id + " не найдена"));
    }
}
