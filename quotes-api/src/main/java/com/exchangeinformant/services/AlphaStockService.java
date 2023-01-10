package com.exchangeinformant.services;

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
public interface AlphaStockService {
    List<Info> getAllStocks();
    void updateAllStocks();
    Info getStockByCode(String code);
    List<Info> getStocksByCodes(List<String> codes);

}
