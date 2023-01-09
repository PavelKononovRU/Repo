package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.AlphaStock;
import com.exchangeinformant.repository.CompanyRepository;
import com.exchangeinformant.repository.AlphaStockRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
    private final AlphaStockRepository stockRepository;
    private final CompanyRepository companyRepository;


    public AlphaStockServiceImpl(AlphaVantageConfig properties, WebClient webClient, AlphaStockRepository stockRepository, CompanyRepository companyRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
    }

    public AlphaStock getStockPrice(String stockName)  {
        return Objects.requireNonNull(webClient
                        .get()
                        .uri(String.format(properties.getUrl(), properties.getGlobalFunction(), stockName, properties.getKey()))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Root.class)
                        .block())
                .getAlphaStock();
    }

    @Override
    public List<Company> getAllStocks() {
        return companyRepository.findAll();
    }

    @Override
    public void updateAllStocks() {
        List<Company> allCompanies = companyRepository.findAll();
        for(Company company : allCompanies){
            AlphaStock updatedStock = getStockPrice(company.getSecureCode());
            updatedStock.setCompany(company);
            stockRepository.save(updatedStock);
        }
    }

    @Override
    public Company getStockByCode(String code) {
        return companyRepository.findCompanyBySecureCode(code);
    }

    @Override
    public List<Company> getStocksByCodes(List<String> codes) {
        List<Company> result = new ArrayList<>();
        for (String code : codes) {
            result.add(companyRepository.findCompanyBySecureCode(code));
        }
        return result;
    }


}