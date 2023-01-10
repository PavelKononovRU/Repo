package com.exchangeinformant.services;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface AlphaStockService {
    List<Stock> getAllStocks();
    void updateAllStocks();
    Stock getStockByCode(String code);
    List<Stock> getStocksByCodes(List<String> codes);

}
