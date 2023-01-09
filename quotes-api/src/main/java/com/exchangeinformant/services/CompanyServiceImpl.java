package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.TinkoffStock;
import com.exchangeinformant.repository.CompanyRepository;
import com.exchangeinformant.repository.StockRepository;
import com.exchangeinformant.repository.TinkoffRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
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
    private final TinkoffRepository tinkoffRepository;

    public CompanyServiceImpl(AlphaVantageConfig properties, WebClient webClient, StockRepository stockRepository, CompanyRepository companyRepository,
                              TinkoffRepository tinkoffRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
        this.tinkoffRepository = tinkoffRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyInfo(String stockName) {
        if(isCompanyPresent(stockName)){
            Company foundCompany = companyRepository.findCompanyBySecureCode(stockName);
            if(areStocksAvailable(stockName)){
                foundCompany.setStocks(new HashSet<>(tinkoffRepository.findAll()));
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

    private boolean isCompanyPresent(String stockName) {
        return getAllCompanies().stream().anyMatch(s->s.getSecureCode().equals(stockName));
    }
    private boolean areStocksAvailable(String stockName) {
        return stockRepository.findAll().stream().anyMatch(s->s.getSymbol().equals(stockName));
    }

}
