package com.exchangeinformant.services;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.TinkoffStock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.TinkoffRepository;
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
    private final TinkoffRepository tinkoffRepository;
    private final InfoRepository infoRepository;


    public TinkoffStock getStockByTicker(String ticker) {

        MarketContext context = openApi.getMarketContext();
        var list = context.searchMarketInstrumentsByTicker(ticker);
        List<MarketInstrument> miList =list.join().getInstruments();
        if (miList.isEmpty()) {
            throw new RuntimeException("Stock not found");
        }
        MarketInstrument item = miList.get(0);

        var lastPrice= context.getMarketOrderbook(item.getFigi(),0).join().get().getLastPrice();


        return new TinkoffStock(
                item.getTicker(),
                lastPrice,
                LocalDateTime.now()
                );
    }


    @Override
    public List<Info> getStocksByCodes(List<String> codes) {
        List<Info> result = new ArrayList<>();
        for(String code : codes){
            result.add(infoRepository.findCompanyBySecureCode(code));
        }
        return result;
    }



    @Override
    public void updateAllStocks() {
        List<Info> companies = infoRepository.findAll();
        for (Info stock : companies) {
            TinkoffStock updatedStock = getStockByTicker(stock.getSecureCode());
            updatedStock.setInfo(stock);
            tinkoffRepository.save(updatedStock);
        }
    }

    @Override
    public List<Info> getAllStocks() {
        return infoRepository.findAll();
    }

    @Override
    public Info getStockByCode(String code) {
        return infoRepository.findCompanyBySecureCode(code);
    }
}
