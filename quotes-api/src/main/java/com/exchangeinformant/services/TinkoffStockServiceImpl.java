package com.exchangeinformant.services;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Currency;
import com.exchangeinformant.model.TinkoffStock;
import lombok.RequiredArgsConstructor;
import org.hibernate.loader.LoaderLogging;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.CandleResolution;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TinkoffStockServiceImpl implements TinkoffStockService {
    private final OpenApi openApi;


    @Override
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
}
