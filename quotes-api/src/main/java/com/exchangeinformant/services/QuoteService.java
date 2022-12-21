package com.exchangeinformant.services;

import com.exchangeinformant.model.Stock;
import org.springframework.stereotype.Service;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface QuoteService {
    Stock getCurrentStock(String stockName);
}
