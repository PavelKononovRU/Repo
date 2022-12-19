package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Stock;
import com.exchangeinformat.userprofile.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getAllStockServices() {
        return stockRepository.findAll();
    }

    @Override
    @Transactional
    public void createStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public Stock getStock(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    @Transactional
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
