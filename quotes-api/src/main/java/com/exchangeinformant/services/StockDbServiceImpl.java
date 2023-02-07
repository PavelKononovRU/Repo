package com.exchangeinformant.services;

import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesException;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 16.01.2023
 * Time: 11:07
 */
@Service
public class StockDbServiceImpl implements StockDbService {

    private final StockRepository stockRepository;
    private final InfoRepository infoRepository;

    @Value("${quotes.supplier}")
    private String serviceName;

    public StockDbServiceImpl(StockRepository stockRepository, InfoRepository infoRepository) {
        this.stockRepository = stockRepository;
        this.infoRepository = infoRepository;
    }

    @Override
    public Stock getStock(String stockName) {
        Stock stock = stockRepository.findBySecureCode(stockName);
        Info info = infoRepository.findFirstBySecureCodeOrderByUpdatedAt(stockName);
        if (info == null) {
            throw new QuotesException(ErrorCodes.NO_INFO.name());
        }
        stock.setInfoList(Collections.singletonList(info));
        return stock;
    }

    @Override
    public Stock getStockByDate(String stockName, LocalDateTime dateFrom, LocalDateTime dateTo) {
        Stock stock = stockRepository.findBySecureCode(stockName);
        List<Info> infoList = infoRepository.getInfoBySecureCodeAndDates(stockName, dateFrom, dateTo);
        if (infoList.isEmpty()) {
            throw new QuotesException(ErrorCodes.NO_INFO.name());
        }
        stock.setInfoList(infoList);
        return stock;
    }

    @Override
    public List<Stock> getAllStocksByDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<Stock> stockList = stockRepository.findAll();
        for (Stock stock : stockList) {
            List<Info> infoList = infoRepository.getInfoBySecureCodeAndDates(stock.getSecureCode(), dateFrom, dateTo);
            if (!infoList.isEmpty()) {
                stock.setInfoList(infoList);
            }
        }
        return stockList.stream().filter(n -> !n.getInfoList().isEmpty()).collect(Collectors.toList());
    }

    @Override
    public List<Stock> getAllAvailableStocksByCodes(List<String> codes) {
        List<Stock> result = new ArrayList<>();
        for (String code : codes) {
            Stock expectedStock = stockRepository.findBySecureCode(code);
            if(expectedStock!=null){
                result.add(expectedStock);
            }
        }
        return result;
    }

    @Override
    public List<Stock> getAllStocks()    {
        return stockRepository.findAllBySource(serviceName);
    }

}
