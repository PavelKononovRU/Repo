package com.exchangeinformant.services;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.TinkoffStock;
import com.exchangeinformant.repository.CompanyRepository;
import com.exchangeinformant.repository.TinkoffRepository;
import lombok.RequiredArgsConstructor;
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
public class TinkoffStockServiceImpl implements TinkoffStockService {
    private final OpenApi openApi;
    private final TinkoffRepository tinkoffRepository;
    private final CompanyRepository companyRepository;


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
    public TinkoffStockDTO getStocksByTickers(List<String> tickers) {
        MarketContext context = openApi.getMarketContext();
        List<CompletableFuture<MarketInstrumentList>> marketInstrument = new ArrayList<>();
        tickers.forEach(t->marketInstrument.add(context.searchMarketInstrumentsByTicker(t)));

        List<TinkoffStock> stocks = marketInstrument.stream()
                .map(CompletableFuture::join)
                .map(mi->{
                    if (!mi.getInstruments().isEmpty()) {
                        return mi.getInstruments().get(0);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(mi -> new TinkoffStock(
                        mi.getTicker(),
                        context.getMarketOrderbook(mi.getFigi(),0).join().get().getLastPrice(),
                        LocalDateTime.now()
                        ))
                .collect(Collectors.toList());
        return new TinkoffStockDTO(stocks);
    }



    @Override
    public void updateAllStocks() {
        List<Company> allStocks = companyRepository.findAll();
        for (Company stock : allStocks) {
            TinkoffStock updatedStock = getStockByTicker(stock.getSecureCode());
            tinkoffRepository.save(updatedStock);
        }
    }

    @Override
    public List<Company> getAllStocks() {
        return companyRepository.findAll();
    }

    @Override
    public Company getStockByCode(String code) {
        return companyRepository.findCompanyBySecureCode(code);
    }
}
