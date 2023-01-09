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
    public List<Company> getStocksByCodes(List<String> codes) {
        List<Company> result = new ArrayList<>();
        for(String code : codes){
            result.add(companyRepository.findCompanyBySecureCode(code));
        }
        return result;
    }



    @Override
    public void updateAllStocks() {
        List<Company> companies = companyRepository.findAll();
        for (Company stock : companies) {
            TinkoffStock updatedStock = getStockByTicker(stock.getSecureCode());
            updatedStock.setCompany(stock);
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
