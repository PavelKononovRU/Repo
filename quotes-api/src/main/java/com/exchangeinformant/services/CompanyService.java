package com.exchangeinformant.services;

import com.exchangeinformant.model.Company;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyInfo(String stockName) throws IOException, URISyntaxException, InterruptedException;
    void save(Company company);
}
