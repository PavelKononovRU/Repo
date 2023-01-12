package com.exchangeinformant.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:23
 */
@Service
public class ScheduleStockServiceImpl implements ScheduleStockService {
    private final StockServiceSwitcher stockServiceSwitcher;

    public ScheduleStockServiceImpl(StockServiceSwitcher stockServiceSwitcher) {
        this.stockServiceSwitcher = stockServiceSwitcher;
    }
    @Override
    @Scheduled(cron = "*/30 * * * * *")
    public void updateAllStocks() {
        StockService stockService = stockServiceSwitcher.getCurrentService();
        stockService.updateAllStocks();
    }
}

