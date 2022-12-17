package com.exchangeinformat.userprofile;

import com.exchangeinformat.userprofile.repository.TariffRepository;
import com.exchangeinformat.userprofile.service.TariffService;
import com.exchangeinformat.userprofile.service.TariffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }

}
