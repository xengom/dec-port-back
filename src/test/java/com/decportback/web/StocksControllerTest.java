package com.decportback.web;

import com.decportback.domain.stocks.Stocks;
import com.decportback.domain.stocks.StocksRepository;
import com.decportback.web.dto.StockSaveRequestDto;
import com.decportback.web.dto.StockUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    private final String equityAccount = "키움";
    private final String countryCode = "US";
    private final String exchangeMarket = "NAS";
    private final String ticker = "NVDA";
    private final String stockName = "Nvidia Corporation";
    private final Integer amount = 6;
    private final Double averagePrice = 859.35;

    @Test
    public void stock_등록() throws Exception {
        // given
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

    @Test
    public void stock_수정() throws Exception {
        // given
        Stocks savedStock = stocksRepository.save(Stocks.builder()
                .amount(amount)
                .averagePrice(averagePrice)
                .countryCode(countryCode)
                .equityAccount(equityAccount)
                .stockName(stockName)
                .ticker(ticker)
                .exchangeMarket(exchangeMarket).build());

        Long updatedStockId = savedStock.getId();
        Integer expectedStockAmount = 1;
        Double expectedStockAveragePrice = 999.99;

        StockUpdateRequestDto requestDto = StockUpdateRequestDto.builder()
                .amount(expectedStockAmount)
                .averagePrice(expectedStockAveragePrice).build();

        String url = "http://localhost:" + port + "/api/v1/stocks/" + updatedStockId;

        HttpEntity<StockUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Stocks> all = stocksRepository.findAll();
        assertThat(all.getFirst().getAmount()).isEqualTo(expectedStockAmount);
        assertThat(all.getFirst().getAveragePrice()).isEqualTo(expectedStockAveragePrice);

    }

    @AfterEach
    public void tearDown() throws Exception {
        stocksRepository.deleteAll();
    }
}