package com.exchangeinformant.services;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 12.01.2023
 * Time: 23:36
 */
@Service
public class StockServiceSwitcherImpl implements StockServiceSwitcher{

    boolean switcher = true;

    private final StockService stockService1;

    private final StockService stockService2;

    public StockServiceSwitcherImpl(@Qualifier("bcsStockService") StockService stockService1, @Qualifier("tinkoffStockService") StockService stockService2) {
        this.stockService1 = stockService1;
        this.stockService2 = stockService2;
    }


    @Override
    public StockService getCurrentService() {
        if (switcher) {
            switcher = false;
            System.out.println("bcs");
            return stockService1;
        }
        switcher = true;
        System.out.println("tinkoff");
        return stockService2;
    }
}
