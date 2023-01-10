package com.exchangeinformant.services;

import com.exchangeinformant.model.Info;

import java.util.List;

public interface TinkoffStockService {
    List<Info> getStocksByCodes(List<String> codes);

    void updateAllStocks();

    List<Info> getAllStocks();
    Info getStockByCode(String code);
}
