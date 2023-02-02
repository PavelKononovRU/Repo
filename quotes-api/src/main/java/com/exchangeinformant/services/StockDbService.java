package com.exchangeinformant.services;

import com.exchangeinformant.model.Stock;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 16.01.2023
 * Time: 11:08
 */

public interface StockDbService {

    Stock getStock(String stockName);

    Stock getStockByDate(String stockName, LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Stock> getAllStocksByDate(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Stock> getAllAvailableStocksByCodes(List<String> codes);

    List<Stock> getAllStocks();


}
