package com.exchangeinformant.services;

import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesException;
import com.exchangeinformant.repository.NameRepositoryRedis;
import com.exchangeinformant.util.Name;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import com.exchangeinformant.util.Tinkoff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Tinkoff
@Slf4j
public class TinkoffStockService implements StockService {
    private final InfoRepository infoRepository;
    private final StockRepository stockRepository;
    private final NameRepositoryRedis nameRepository;
    private final OpenApi openApi;


    @Scheduled(cron = "0 */2 * * * *")
    @Override
    public void updateAllStocks() {
        if(nameRepository.findAll().isEmpty()){
            getAllStocks();
        }
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            Info updatedStock = getInfoByCode(stock.getSecureCode());
            infoRepository.save(updatedStock);
        }
        log.info("Updated Successfully");
    }

    @Override
    public void getAllStocks() {
        MarketContext context = openApi.getMarketContext();
        CompletableFuture<MarketInstrumentList> marketList = context.getMarketStocks();
        List<MarketInstrument> miList = marketList.join().getInstruments();
        for (MarketInstrument mi : miList) {
            nameRepository.save(new Name(mi.getTicker(),mi.getName(),mi.getCurrency().name()));
        }
        saveAllStocks();
    }

    @Override
    public Stock getStockDirectly(String secureCode) {
        Stock stock;
        if (stockRepository.existsBySecureCode(secureCode)) {
            stock = stockRepository.findBySecureCode(secureCode);
        }else{
            Name name = nameRepository.get(secureCode);
            stock = new Stock(name.getSecureCode(), name.getIssuer(), name.getCurrency(), new ArrayList<>());
        }
        MarketContext context = openApi.getMarketContext();
        var list = context.searchMarketInstrumentsByTicker(secureCode);
        List<MarketInstrument> miList =list.join().getInstruments();
        MarketInstrument item = miList.get(0);

        var lastPrice= context.getMarketOrderbook(item.getFigi(),0).join().get().getLastPrice();

        stock.getInfoList().add(new Info(
                lastPrice.doubleValue(),
                LocalDateTime.now(),
                item.getTicker())
        );

        return stock;
    }

    @Override
    public List<Stock> getStocksDirectly(List<String> secureCodes) {
        MarketContext context = openApi.getMarketContext();
        List<CompletableFuture<MarketInstrumentList>> marketInstruments = new ArrayList<>();
        secureCodes.forEach(code -> marketInstruments.add(getMarketInstrumentTicker(code)));
        return   marketInstruments.stream()
                .map(CompletableFuture::join)
                .map(mi -> {
                    if (!mi.getInstruments().isEmpty()) {
                        return mi.getInstruments().get(0);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(mi -> new Stock(
                        mi.getTicker(),
                        mi.getName(),
                        mi.getCurrency().name(),
                        new ArrayList<>(List.of(
                                new Info(context.getMarketOrderbook(mi.getFigi(), 0).join().orElseThrow(() -> new QuotesException(ErrorCodes.NO_PRICE.getErrorMessage())).getLastPrice().doubleValue(), LocalDateTime.now(), mi.getTicker())))
                        ))
                .collect(Collectors.toList());
    }

    private CompletableFuture<MarketInstrumentList> getMarketInstrumentTicker(String secureCode) {
        MarketContext context = openApi.getMarketContext();
        return context.searchMarketInstrumentsByTicker(secureCode);
    }


    private void saveAllStocks() {
        nameRepository.findAll().stream()
                .limit(200)
                .forEach(n -> stockRepository.save(new Stock(n.getSecureCode(),n.getIssuer(),n.getCurrency())));
    }


    private Info getInfoByCode(String secureCode) {
        MarketContext context = openApi.getMarketContext();
        var list = context.searchMarketInstrumentsByTicker(secureCode);
        List<MarketInstrument> miList =list.join().getInstruments();
        if (miList.isEmpty()) {
            throw new RuntimeException("Stock not found");
        }
        MarketInstrument item = miList.get(0);

        var lastPrice= context.getMarketOrderbook(item.getFigi(),0).join().get().getLastPrice();

        return new Info(
                lastPrice.doubleValue(),
                LocalDateTime.now(),
                item.getTicker()
        );
    }
}
