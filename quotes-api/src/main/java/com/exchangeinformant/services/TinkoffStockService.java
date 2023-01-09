package com.exchangeinformant.services;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Company;

import java.util.List;

public interface TinkoffStockService {
    List<Company> getStocksByCodes(List<String> codes);

    void updateAllStocks();

    List<Company> getAllStocks();
    Company getStockByCode(String code);
}
