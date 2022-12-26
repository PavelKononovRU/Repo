package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Tariff;

import java.util.List;

public interface TariffService {

    List<Tariff> getAllTariff();
    void createTariff(Tariff tariff);

    Tariff getTariff(Long id);

    void updateTariff(Tariff tariff);

    void deleteTariff(Long id);
}
