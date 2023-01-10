package com.exchangeinformant.services;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TinkoffStockServiceImpl implements TinkoffStockService {
    private final OpenApi openApi;
    private final StockRepository stockRepository;
    private final InfoRepository infoRepository;


    public Info getStockByTicker(String ticker) {

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


    @Override
    public List<Stock> getStocksByCodes(List<String> codes) {
        List<Stock> result = new ArrayList<>();
        for(String code : codes){
            result.add(stockRepository.findBySecureCode(code));
        }
        return result;
    }



    @Override
    public void updateAllStocks() {
        List<Stock> companies = stockRepository.findAll();
        for (Stock stock : companies) {
            Info updatedStock = getStockByTicker(stock.getSecureCode());
            infoRepository.save(updatedStock);
        }
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockByCode(String code) {
        return stockRepository.findBySecureCode(code);
    }
}
