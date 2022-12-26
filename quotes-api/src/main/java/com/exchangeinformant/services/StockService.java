package com.exchangeinformant.services;

import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.Company;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface StockService {
    Stock getStockPrice(String stockName) throws IOException, URISyntaxException, InterruptedException;
    List<Stock> getAllStocks();
}
