package com.exchangeinformant.repository;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    void itShouldFindStock() {
        String secureCode = "AAPL";
        Stock stock = new Stock(secureCode, "Apple" ,"USD", new ArrayList<Info>());

        stockRepository.save(stock);

        Stock expected = stockRepository.findBySecureCode(secureCode);

        Assertions.assertThat(expected.getSecureCode()).isEqualTo(stock.getSecureCode());
    }

    @Test
    void itShouldNotFindStock() {
        String secureCode = "AAPL";

        Stock expected = stockRepository.findBySecureCode(secureCode);

        Assertions.assertThat(expected).isNull();
    }
}