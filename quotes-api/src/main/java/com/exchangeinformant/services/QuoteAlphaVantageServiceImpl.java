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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


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
    public Stock getStockPrice(String stockName) throws IOException, URISyntaxException, InterruptedException {
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
        if(getAllStocksInfo().contains(getStockInfo(stockName))){
            stock.setStockInfo(getStockInfo(stockName));
        }
        stockRepository.save(stock);
        return stock;
    }

    @Override
    public StockInfo getStockInfo(String stockName) throws IOException, URISyntaxException, InterruptedException {
        if(getAllStocksInfo().stream().anyMatch(s->s.getSymbol().equals(stockName))){
            StockInfo si = getAllStocksInfo().stream().filter(s->s.getSymbol().equals(stockName)).findFirst().get();
            if(getAllStocks().stream().anyMatch(s->s.getSymbol().equals(stockName))){
                Set<Stock> newStockSet = getAllStocks().stream().filter(s->s.getSymbol().equals(stockName)).collect(Collectors.toSet());
                si.setStocks(newStockSet);
            }
            return si;
        }

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

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<StockInfo> getAllStocksInfo() {
        return stockInfoRepository.findAll();
    }
}