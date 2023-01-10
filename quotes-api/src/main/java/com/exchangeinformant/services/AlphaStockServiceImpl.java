package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 9:38
 */
@Service
public class AlphaStockServiceImpl implements AlphaStockService {

    private final AlphaVantageConfig properties;
    private final WebClient webClient;
    private final StockRepository stockRepository;
    private final InfoRepository infoRepository;


    public AlphaStockServiceImpl(AlphaVantageConfig properties, WebClient webClient, StockRepository stockRepository, InfoRepository infoRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.infoRepository = infoRepository;
    }


    public Info getStockPrice(String stockName)  {
        return Objects.requireNonNull(webClient
                        .get()
                        .uri(String.format(properties.getUrl(), properties.getGlobalFunction(), stockName, properties.getKey()))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Root.class)
                        .block())
                .getAlphaInfo();
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }


    @Override
    public void updateAllStocks() {
        List<Stock> allCompanies = stockRepository.findAll();
        for(Stock info : allCompanies){
            Info updatedStock = getStockPrice(info.getSecureCode());
            updatedStock.setUpdatedAt(LocalDateTime.now());
            infoRepository.save(updatedStock);
        }
    }

    @Override
    public Stock getStockByCode(String code) {
        return stockRepository.findBySecureCode(code);
    }

    @Override
    public List<Stock> getStocksByCodes(List<String> codes) {
        List<Stock> result = new ArrayList<>();
        for (String code : codes) {
            result.add(stockRepository.findBySecureCode(code));
        }
        return result;
    }


}