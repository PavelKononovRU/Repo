package com.exchangeinformant.services;

import com.exchangeinformant.configuration.BcsConfig;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.Logger;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private final StockRepository stockRepository;

    public BcsStockServiceImpl(WebClient webClient, BcsConfig bcsConfig, StockRepository stockRepository) {
        this.webClient = webClient;
        this.bcsConfig = bcsConfig;
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
            Stock[] stockArray = objectMapper.readValue(string, Stock[].class);
            System.err.println(Arrays.stream(stockArray).findFirst().get());
           // stockRepository.save(stock);
        }
    }
}
