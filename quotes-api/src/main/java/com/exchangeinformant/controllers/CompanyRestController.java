package com.exchangeinformant.controllers;

import com.exchangeinformant.model.Company;
import com.exchangeinformant.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/info",produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyRestController {
    private final CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/{stock}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> getStockInfo(@PathVariable("stock") String stock)  {
        return new ResponseEntity<>(companyService.getCompanyInfo(stock) , HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getAllStockInfo() {
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

}
