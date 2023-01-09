package com.exchangeinformant.services;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.TinkoffStock;

import java.util.List;

public interface TinkoffStockService {
    TinkoffStockDTO getStocksByTickers(List<String> tickers);

    void updateAllStocks();

    List<Company> getAllStocks();
    Company getStockByCode(String code);
}
