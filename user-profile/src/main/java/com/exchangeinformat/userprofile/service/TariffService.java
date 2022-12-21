package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Stock;
import com.exchangeinformat.userprofile.model.Tariff;

import java.util.List;

public interface TariffService {

    List<Tariff> getAllTariff();
    void createTariff(Tariff tariff);

    Tariff getTariff(Long id);

    void updateTariff(Tariff tariff);

    void deleteTariff(Long id);
}
