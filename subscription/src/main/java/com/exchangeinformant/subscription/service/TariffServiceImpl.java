package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService{

    private final TariffRepository tariffRepository;

    @Autowired
    public TariffServiceImpl(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public List<Tariff> getAllTariff() {
        return tariffRepository.findAll();
    }

    @Override
    @Transactional
    public void createTariff(Tariff tariff) {
        tariffRepository.save(tariff);
    }

    @Override
    public Tariff getTariff(Long id) {
        return tariffRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateTariff(Tariff tariff) {
        tariffRepository.save(tariff);
    }

    @Override
    @Transactional
    public void deleteTariff(Long id) {
        tariffRepository.deleteById(id);
    }
}
