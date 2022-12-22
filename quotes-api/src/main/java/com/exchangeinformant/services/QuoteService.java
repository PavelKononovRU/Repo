package com.exchangeinformant.services;

import com.exchangeinformant.dto.StockDto;
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
    StockDto getCurrentStock(String stockName) throws IOException, URISyntaxException, InterruptedException;
}
