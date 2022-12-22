package com.exchangeinformant.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
@Service
public interface QuoteService {
    String getCurrentStock(String stockName) throws IOException, URISyntaxException, InterruptedException;
}
