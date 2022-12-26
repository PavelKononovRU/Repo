package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.repository.CompanyRepository;
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
public class StockServiceImpl implements StockService {

    private final AlphaVantageConfig properties;
    private final WebClient webClient;
    private final StockRepository stockRepository;
    private final CompanyService companyService;


    public StockServiceImpl(AlphaVantageConfig properties, WebClient webClient, StockRepository stockRepository, CompanyService companyService) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.companyService = companyService;
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
        if(companyService.getAllCompanies().contains(companyService.getCompanyInfo(stockName))){
            stock.setCompany(companyService.getCompanyInfo(stockName));
        }
        stockRepository.save(stock);
        return stock;
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}