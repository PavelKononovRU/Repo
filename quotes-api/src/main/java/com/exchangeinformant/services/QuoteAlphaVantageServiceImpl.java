package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.StockInfo;
import com.exchangeinformant.repository.StockInfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private final StockInfoRepository stockInfoRepository;


    public QuoteAlphaVantageServiceImpl(AlphaVantageConfig properties, WebClient webClient, StockRepository stockRepository, StockInfoRepository stockInfoRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.stockInfoRepository = stockInfoRepository;
    }

    @Override
    public Stock getStockPrice(String stockName) {
        Stock stock = Objects.requireNonNull(webClient
                        .get()
                        .uri(String.format(properties.getUrl(), properties.getGlobalFunction(), stockName, properties.getKey()))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Root.class)
                        .block())
                .getStock();
        stock.setCreatedAt(LocalDateTime.now());
        stock.setUpdatedAt(LocalDateTime.now());
        stockRepository.save(stock);
        return stock;
    }

    @Override
    public StockInfo getStockInfo(String stockName) throws IOException, URISyntaxException, InterruptedException {
        StockInfo stockInfo = Objects.requireNonNull(webClient
                        .get()
                        .uri(String.format(properties.getUrl(), properties.getOverviewFunction(), stockName, properties.getKey()))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(StockInfo.class)
                        .block());
        stockInfoRepository.save(stockInfo);
        return stockInfo;
    }
}