package com.exchangeinformant.services;

import com.exchangeinformant.configuration.BcsConfig;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 05.01.2023
 * Time: 17:27
 */
@Service
public class BcsStockServiceImpl implements StockService {

    private final WebClient webClient;
    private final BcsConfig bcsConfig;
    private final InfoRepository infoRepository;

    private final StockRepository stockRepository;

    public BcsStockServiceImpl(WebClient webClient, BcsConfig bcsConfig, InfoRepository infoRepository, StockRepository stockRepository) {
        this.webClient = webClient;
        this.bcsConfig = bcsConfig;
        this.infoRepository = infoRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock getStock(String stockName) {
        return stockRepository.findBySecureCode(stockName);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    //TODO CRON
    public void updateAllStocks() throws JsonProcessingException {
        List<Stock> allStocks = stockRepository.findAll();
        for (Stock stock : allStocks) {
            String string = webClient
                    .get()
                    .uri(bcsConfig.getUrl() + String.format(bcsConfig.getOneStock(), stock.getSecureCode()))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ObjectMapper objectMapper = new ObjectMapper()
                   .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            stock = Arrays.stream((objectMapper.readValue(string, Stock[].class))).findFirst().get();
            double price = objectMapper.readTree(string).get(0).get("info").get("close").asDouble();
            Info info = new Info();
            info.setUpdatedAt(LocalDateTime.now());
            info.setLastPrice(price);
            info.setSecureCode(stock.getSecureCode());
            infoRepository.save(info);
        }
    }
}
