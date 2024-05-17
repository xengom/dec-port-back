package com.decportback.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StocksRepositoryTest {

    @Autowired
    StocksRepository stocksRepository;

    @Test
    public void 주식추가_및_조회_테스트() throws Exception {
        // given
        String equityAccount = "키움";
        String countryCode = "US";
        String exchangeMarket = "NAS";
        String ticker = "NVDA";
        String stockName = "Nvidia Corporation";
        Integer amount = 6;
        Double averagePrice = 859.35;

        stocksRepository.save(Stocks.builder()
                .amount(amount)
                .averagePrice(averagePrice)
                .countryCode(countryCode)
                .equityAccount(equityAccount)
                .stockName(stockName)
                .ticker(ticker)
                .exchangeMarket(exchangeMarket).build());

        // when
        List<Stocks> stocksList = stocksRepository.findAll();

        // then
        Stocks stocks = stocksList.get(0);
        assertEquals(stockName,stocks.getStockName());
    }
    
    
    @AfterEach
    public void cleanUp() {
        stocksRepository.deleteAll();
    }
}