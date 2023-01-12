package com.exchangeinformant.services;

import org.springframework.stereotype.Service;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 12.01.2023
 * Time: 22:30
 */
@Service
public interface StockServiceSwitcher {

    StockService getCurrentService();
}
