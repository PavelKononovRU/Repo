package com.exchangeinformant.services;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;

import java.util.List;

public interface TinkoffStockService {
    List<Stock> getStocksByCodes(List<String> codes);

    void updateAllStocks();

    List<Stock> getAllStocks();
    Stock getStockByCode(String code);
}
