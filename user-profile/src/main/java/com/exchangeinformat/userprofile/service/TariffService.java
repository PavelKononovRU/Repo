package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Tariff;

public interface TariffService {
    void createTariff(Tariff tariff);

    Tariff getTariff(Long id);

    void updateTariff(Tariff tariff);

    void deleteTariff(Long id);
}
