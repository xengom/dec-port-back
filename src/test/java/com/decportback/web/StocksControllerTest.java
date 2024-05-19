package com.decportback.web;

import com.decportback.domain.Stocks;
import com.decportback.domain.StocksRepository;
import com.decportback.web.dto.StockSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StocksControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StocksRepository stocksRepository;

    @Test
    public void stock_등록() throws Exception {
        // given
        String equityAccount = "키움";
        String countryCode = "US";
        String exchangeMarket = "NAS";
        String ticker = "NVDA";
        String stockName = "Nvidia Corporation";
        Integer amount = 6;
        Double averagePrice = 859.35;

        StockSaveRequestDto requestDto = StockSaveRequestDto.builder()
                .amount(amount)
                .averagePrice(averagePrice)
                .countryCode(countryCode)
                .equityAccount(equityAccount)
                .stockName(stockName)
                .ticker(ticker)
                .exchangeMarket(exchangeMarket).build();
        String url = "http://localhost:" + port + "/api/v1/stocks";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Stocks> all = stocksRepository.findAll();
        assertThat(all.getFirst().getTicker()).isEqualTo(ticker);
        assertThat(all.getFirst().getStockName()).isEqualTo(stockName);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stocksRepository.deleteAll();
    }
}