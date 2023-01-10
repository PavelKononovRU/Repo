package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Root;
import com.exchangeinformant.model.AlphaStock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.AlphaStockRepository;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final AlphaStockRepository stockRepository;
    private final InfoRepository infoRepository;


    public AlphaStockServiceImpl(AlphaVantageConfig properties, WebClient webClient, AlphaStockRepository stockRepository, InfoRepository infoRepository) {
        this.properties = properties;
        this.webClient = webClient;
        this.stockRepository = stockRepository;
        this.infoRepository = infoRepository;
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
    public List<Info> getAllStocks() {
        return infoRepository.findAll();
    }


    @Override
    public void updateAllStocks() {
        List<Info> allCompanies = infoRepository.findAll();
        for(Info info : allCompanies){
            AlphaStock updatedStock = getStockPrice(info.getSecureCode());
            updatedStock.setUpdatedAt(LocalDateTime.now());
            updatedStock.setInfo(info);
            stockRepository.save(updatedStock);
        }
    }

    @Override
    public Info getStockByCode(String code) {
        return infoRepository.findCompanyBySecureCode(code);
    }

    @Override
    public List<Info> getStocksByCodes(List<String> codes) {
        List<Info> result = new ArrayList<>();
        for (String code : codes) {
            result.add(infoRepository.findCompanyBySecureCode(code));
        }
        return result;
    }


}