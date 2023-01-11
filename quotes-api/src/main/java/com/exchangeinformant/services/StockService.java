package com.exchangeinformant.services;

import com.exchangeinformant.model.Stock;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface StockService {

    Stock getStock(String stockName);

    List<Stock> getAllStocks();

    void updateAllStocksByBcs() throws IOException;
    void updateAllStocksByTinkoff() throws IOException;
    List<Stock> getStocksByCodes(List<String> codes);
}
