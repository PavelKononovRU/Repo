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
    private final StockService stockService;

    public ScheduleStockServiceImpl(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    @Scheduled(cron = "0 */30 * * * *")
    public void updateAllStocks() {
        stockService.updateAllStocks();
    }

}

