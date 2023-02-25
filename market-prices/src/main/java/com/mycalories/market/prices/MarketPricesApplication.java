package com.mycalories.market.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mycalories")
public class MarketPricesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketPricesApplication.class, args);
    }

}
