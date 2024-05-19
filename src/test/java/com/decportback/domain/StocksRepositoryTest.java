package com.decportback.domain;

import com.decportback.domain.stocks.Stocks;
import com.decportback.domain.stocks.StocksRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StocksRepositoryTest {

    @Autowired
    StocksRepository stocksRepository;

    private final String equityAccount = "키움";
    private final String countryCode = "US";
    private final String exchangeMarket = "NAS";
    private final String ticker = "NVDA";
    private final String stockName = "Nvidia Corporation";
    private final Integer amount = 6;
    private final Double averagePrice = 859.35;
    
    @Test
    public void 주식추가_및_조회_테스트() throws Exception {
        // given
        
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
        Stocks stocks = stocksList.getFirst();
        assertEquals(stockName,stocks.getStockName());
    }
    
    @Test
    public void BaseTimeEntity_등록() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(2024,5,19,0,0,0);
        stocksRepository.save(Stocks.builder()
                .amount(amount)
                .averagePrice(averagePrice)
                .countryCode(countryCode)
                .equityAccount(equityAccount)
                .exchangeMarket(exchangeMarket)
                .stockName(stockName)
                .ticker(ticker).build());
        
        // when
        List<Stocks> stocksList = stocksRepository.findAll();
        
        // then
        Stocks stocks = stocksList.getFirst();
        System.out.println("stocks.getCreatedAt() = " + stocks.getCreatedAt());
        System.out.println("stocks.getUpdatedAt() = " + stocks.getUpdatedAt());
        assertThat(stocks.getCreatedAt().isAfter(now));
        assertThat(stocks.getUpdatedAt().isAfter(now));
        
    }
    
    @AfterEach
    public void cleanUp() {
        stocksRepository.deleteAll();
    }
}