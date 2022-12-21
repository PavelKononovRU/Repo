package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStock();

    void createStock(Stock stock);

    Stock getStock(Long id);

    void updateStock(Stock stock);

    void deleteStock(Long id);
}
