package com.exchangeinformant.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "stockClient",url =" https://api.bcs.ru")
public interface StockClient {
    @RequestMapping(method = RequestMethod.GET, value = "/partner/quotations?portfolio_ids%5B0%5D=115&limit=500",produces = "application/json")
    Root findAllStocks();
}
