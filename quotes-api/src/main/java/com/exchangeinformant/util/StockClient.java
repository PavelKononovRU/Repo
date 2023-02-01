package com.exchangeinformant.util;

import com.exchangeinformant.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "stockClient")
public interface StockClient {
    @RequestMapping(method = RequestMethod.GET, value = "${bcs.api.all-stocks}")
    Root findAllStocks();
    @RequestMapping(method = RequestMethod.GET, value = "${bcs.api.one-stock}")
    List<StockDTO> findOneStock(@PathVariable("secureCode") String secureCode);
}
