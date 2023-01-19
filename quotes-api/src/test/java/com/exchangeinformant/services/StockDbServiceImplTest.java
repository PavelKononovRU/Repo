package com.exchangeinformant.services;

import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
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
        stockDbService.getAllStocks();

        Mockito.verify(stockRepository).findAll();
    }
}