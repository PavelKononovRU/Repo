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
    public void updateAllStocks() throws IOException {
        String rt = webClient
                .get()
                .uri(bcsConfig.getUrl())
                .header("partner-token", bcsConfig.getPartnerToken())
                .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0)")
                .retrieve()
                .bodyToMono(String.class)
                .cache()
                .log()
                .block();
        System.out.println(rt.length());
    }
}
