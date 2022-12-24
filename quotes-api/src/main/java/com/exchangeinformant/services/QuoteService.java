package com.exchangeinformant.services;

import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.StockInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface QuoteService {
    Stock getStockPrice(String stockName) throws IOException, URISyntaxException, InterruptedException;
    StockInfo getStockInfo(String stockName) throws IOException, URISyntaxException, InterruptedException;
}
