package com.exchangeinformant.services;

import com.exchangeinformant.repository.NameRepositoryRedis;
import com.exchangeinformant.util.Name;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import com.exchangeinformant.util.Tinkoff;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Tinkoff
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
            Info updatedStock = getStockByTicker(stock.getSecureCode());
            infoRepository.save(updatedStock);
        }
        System.out.printf("%s: Updated Successfully%n", LocalDateTime.now());
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


    private void saveAllStocks() {
        nameRepository.findAll().stream()
                .limit(200)
                .forEach(n -> stockRepository.save(new Stock(n.getSecureCode(),n.getIssuer(),n.getCurrency())));
    }

    private Info getStockByTicker(String ticker) {
        MarketContext context = openApi.getMarketContext();
        var list = context.searchMarketInstrumentsByTicker(ticker);
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
