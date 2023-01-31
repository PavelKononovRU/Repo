package com.exchangeinformant.services;

import com.exchangeinformant.dto.NameDTO;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.NameRepository;
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
    private final NameRepository nameRepository;
    private final OpenApi openApi;


    @Override
    public void updateAllStocks() {
        List<Stock> companies = stockRepository.findAll();
        for (Stock stock : companies) {
            Info updatedStock = getStockByTicker(stock.getSecureCode());
            infoRepository.save(updatedStock);
        }
        System.out.printf("%s: Updated Successfully%n", LocalDateTime.now());
    }

    @Scheduled(cron = "0 */1 * * * *")
    @Override
    public void getAllStocks() {
        MarketContext context = openApi.getMarketContext();
        CompletableFuture<MarketInstrumentList> marketList = context.getMarketStocks();
        List<MarketInstrument> miList = marketList.join().getInstruments();
        for (MarketInstrument mi : miList) {
            nameRepository.save(new NameDTO(mi.getTicker(),mi.getCurrency().name(),mi.getName()));
        }
        System.out.printf("%s: Found Names Successfully%n", LocalDateTime.now());
    }

    @Override
    public List<NameDTO> getAllNames() {
        return null;
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
