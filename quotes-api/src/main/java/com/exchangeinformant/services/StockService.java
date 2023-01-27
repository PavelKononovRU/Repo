package com.exchangeinformant.services;

import com.exchangeinformant.dto.NameDTO;
import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.model.Info;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface StockService {

    void updateAllStocks();

    List<String> getAllStocks();

    //TODO PREMIUM
//    Stock getStockDirectly(String stockName);
}
