package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.CompanyRepository;
import com.exchangeinformant.repository.StockRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final AlphaVantageConfig properties;
    private final WebClient webClient;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(AlphaVantageConfig properties, WebClient webClient, StockRepository stockRepository, CompanyRepository companyRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyInfo(String stockName) throws IOException, URISyntaxException, InterruptedException {
        if(getAllCompanies().stream().anyMatch(s->s.getSymbol().equals(stockName))){
            Company foundCompany = getAllCompanies().stream().filter(s->s.getSymbol().equals(stockName)).findFirst().get();
            if(stockRepository.findAll().stream().anyMatch(s->s.getSymbol().equals(stockName))){
                Set<Stock> newStockSet = stockRepository.findAll().stream().filter(s->s.getSymbol().equals(stockName)).collect(Collectors.toSet());
                foundCompany.setStocks(newStockSet);
            }
            return foundCompany;
        }

        Company company = Objects.requireNonNull(webClient
                .get()
                .uri(String.format(properties.getUrl(), properties.getOverviewFunction(), stockName, properties.getKey()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Company.class)
                .block());
        companyRepository.save(company);
        return company;
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }
}
