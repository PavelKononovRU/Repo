package com.exchangeinformant.services;

import com.exchangeinformant.configuration.AlphaVantageConfig;
import com.exchangeinformant.dto.Root;
import com.exchangeinformant.dto.StockDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public QuoteAlphaVantageServiceImpl(AlphaVantageConfig properties, WebClient webClient) {
        this.properties = properties;
        this.webClient = webClient;
    }

    @Override
    public StockDto getCurrentStock(String stockName) {
        return Objects.requireNonNull(webClient.get()
                        .uri(String.format(properties.getUrl(), properties.getFunction(), stockName, properties.getKey()))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Root.class)
                        .block())
                .getStockDto();
    }
}