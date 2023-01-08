package com.exchangeinformant.services;

import com.exchangeinformant.configuration.BcsConfig;
import com.exchangeinformant.dto.InfoDTO;
import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;

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
    private final InfoRepository infoRepository;

    private final StockRepository stockRepository;

    public BcsStockServiceImpl(WebClient webClient, BcsConfig bcsConfig, InfoRepository infoRepository, StockRepository stockRepository) {
        this.webClient = webClient;
        this.bcsConfig = bcsConfig;
        this.infoRepository = infoRepository;
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

    @Scheduled(cron = "0 */1 * * * *")
    public void updateAllStocks() {

        List<Stock> allStocks = stockRepository.findAll();
        for (Stock stock : allStocks) {
            try {
                Mono<List<StockDTO>> mono = webClient
                        .get()
                        .uri(bcsConfig.getUrl() + String.format(bcsConfig.getOneStock(), stock.getSecureCode()))
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<>() {});
                StockDTO stockDTO = Objects.requireNonNull(mono.block()).get(0);
                InfoDTO infoDTO = stockDTO.getInfoList();
                Info info = convertInfoDTOToInfo(infoDTO);
                info.setSecureCode(stock.getSecureCode());
                infoRepository.save(info);
            } catch (WebClientRequestException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%s: Updated Successfully%n", LocalDateTime.now());
    }

    private Info convertInfoDTOToInfo(InfoDTO infoDTO) {
        Info info = new Info();
        info.setUpdatedAt(LocalDateTime.now());
        info.setLastPrice(infoDTO.getLastPrice());
        return info;
    }
}
