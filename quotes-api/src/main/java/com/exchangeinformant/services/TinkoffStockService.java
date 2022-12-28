package com.exchangeinformant.services;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.TinkoffStock;

import java.util.List;

public interface TinkoffStockService {
    TinkoffStock getStockByTicker(String ticker);
    TinkoffStockDTO getStocksByTickers(List<String> tickers);
}
