package com.exchangeinformant.services;

import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesException;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
                add(new Info(1,BigDecimal.valueOf(34d),LocalDateTime.now(),"AAPL"));
            }
        });


        when(stockRepository.findBySecureCode("AAPL")).thenReturn(stock);
        when(infoRepository.findFirstBySecureCodeOrderByUpdatedAt("AAPL")).thenReturn(stock.getInfoList().get(0));

        assertEquals("USD", stockDbService.getStock("AAPL").getCurrency());
        assertEquals("Apple", stockDbService.getStock("AAPL").getIssuer());
    }    @Test
    void shouldThrowQuotesException() {
        when(infoRepository.findFirstBySecureCodeOrderByUpdatedAt("AAPL")).thenThrow(new QuotesException(ErrorCodes.NO_INFO.getErrorMessage()));

        Exception exception = assertThrows(QuotesException.class, () ->{
            infoRepository.findFirstBySecureCodeOrderByUpdatedAt("AAPL");
        });

        assertTrue(exception.getMessage().contains("Stocks not found, try to increase condition"));
    }

    @Test
    void shouldGetStockByDateAndCode() {
        LocalDateTime actualTime = LocalDateTime.of(2021, Month.APRIL, 24, 14, 33, 48);
        Stock stock = new Stock("AAPL", "Apple" ,"USD", new ArrayList<>(){
            {
                add(new Info(1, BigDecimal.valueOf(34d),actualTime,"AAPL"));
            }
        });


        when(stockRepository.findBySecureCode("AAPL")).thenReturn(stock);
        when(infoRepository.getInfoBySecureCodeAndDates("AAPL",LocalDateTime.of(2020, Month.JANUARY, 1, 8, 0),LocalDateTime.of(2021, Month.DECEMBER, 31, 12, 0))).thenReturn(stock.getInfoList());

        assertEquals(actualTime, stockDbService.getStockByDate("AAPL",LocalDateTime.of(2020, Month.JANUARY, 1, 8, 0),LocalDateTime.of(2021, Month.DECEMBER, 31, 12, 0)).getInfoList().get(0).getUpdatedAt());
    }

    @Test
    void shouldGetAllStocksByListOfCodes() {
        List<String> codes = new ArrayList<>();
        codes.add("NVDA");
        codes.add("AAPL");
        Stock stock = new Stock("AAPL", "Apple" ,"USD", new ArrayList<>(){
            {
                add(new Info(1,BigDecimal.valueOf(34d),LocalDateTime.now(),"AAPL"));
            }
        });
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);

        when(stockRepository.findBySecureCode(stock.getSecureCode())).thenReturn(stock);


        assertEquals(stocks, stockDbService.getAllAvailableStocksByCodes(codes));
    }


}