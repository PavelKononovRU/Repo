package com.exchangeinformant.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:23
 */
@Service
public class ScheduleStockServiceImpl implements ScheduleStockService {

    boolean switcher = true;

    private final StockService bcs;

    private final StockService tinkoff;

    public ScheduleStockServiceImpl(@Qualifier("bcsStockService") StockService bcs, @Qualifier("tinkoffStockService") StockService tinkoff) {
        this.bcs = bcs;
        this.tinkoff = tinkoff;
    }

    @Override
    @Scheduled(cron = "0 */10 * * * *")
    public void updateAllStocks() {
        if (switcher) {
            switcher = false;
            bcs.updateAllStocks();
        } else {
            switcher = true;
            tinkoff.updateAllStocks();
        }
    }
}
