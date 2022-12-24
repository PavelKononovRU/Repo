package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.StockRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 9:38
 */
@Service
public class QuoteAlphaVantageServiceImpl implements QuoteService {

    private final AlphaVantageConfig properties;
    private final WebClient webClient;

    private final StockRepository stockRepository;


    public QuoteAlphaVantageServiceImpl(AlphaVantageConfig properties, WebClient webClient, StockRepository stockRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock getCurrentStock(String stockName) {
        Stock stock = Objects.requireNonNull(webClient
                        .get()
                        .uri(String.format(properties.getUrl(), properties.getFunction(), stockName, properties.getKey()))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Root.class)
                        .block())
                .getStock();
        stock.setCreatedAt(LocalDateTime.now());
        stockRepository.save(stock);
        return stock;
    }
}