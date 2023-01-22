package com.exchangeinformant.services;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class StockDbServiceImplTest {

    @Mock
    private StockRepository stockRepository;
    @Mock
    private InfoRepository infoRepository;
    private StockDbService stockDbService;

    @BeforeEach
    void setUp() {
        stockDbService = new StockDbServiceImpl(stockRepository,infoRepository);
    }



    @Test
    void canGetAllStocks() {
        stockDbService.getAllStocksByDate(LocalDateTime.MIN,LocalDateTime.MAX);

        Mockito.verify(stockRepository).findAll();
    }

    @Test
    void shouldGetStockByCode() {
        Stock stock = new Stock("AAPL", "Apple" ,"USD", new ArrayList<>(){
            {
                add(new Info(1,34d,LocalDateTime.now(),"AAPL"));
            }
        });

        stockRepository.save(stock);
        infoRepository.save(stock.getInfoList().get(0));
        when(stockDbService.getStock("AAPL")).thenReturn(stock);

        assertEquals(stockDbService.getStock("AAPL").getCurrency(),"USD");
        assertEquals(stockDbService.getStock("AAPL").getIssuer(),"Apple");
    }

    @Test
    @Disabled
    void shouldGetStockByDateAndCode() {

    }

    @Test
    @Disabled
    void shouldGetAllStocksByListOfCodes() {

    }


}