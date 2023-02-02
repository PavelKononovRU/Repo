package com.exchangeinformant.util;

import com.exchangeinformant.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "stockClient")
public interface StockClient {
    @GetMapping(value = "${bcs.api.all-stocks}")
    Root findAllStocks();
    @GetMapping(value = "${bcs.api.one-stock}")
    List<StockDTO> findOneStock(@PathVariable("secureCode") String secureCode);
}
