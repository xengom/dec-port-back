package com.decportback.web.dto;

import com.decportback.domain.stocks.Stocks;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockSaveRequestDto {

    private String equityAccount;
    private String countryCode;
    private String exchangeMarket;
    private String ticker;
    private String stockName;
    private Integer amount;
    private Double averagePrice;

    @Builder
    public StockSaveRequestDto(String equityAccount,
                               String countryCode,
                               String exchangeMarket,
                               String ticker,
                               String stockName,
                               Integer amount,
                               Double averagePrice) {
        this.equityAccount = equityAccount;
        this.countryCode = countryCode;
        this.exchangeMarket = exchangeMarket;
        this.ticker = ticker;
        this.stockName = stockName;
        this.amount = amount;
        this.averagePrice = averagePrice;
    }

    public Stocks toEntity() {
        return Stocks.builder()
                .equityAccount(equityAccount)
                .countryCode(countryCode)
                .exchangeMarket(exchangeMarket)
                .ticker(ticker)
                .stockName(stockName)
                .amount(amount)
                .averagePrice(averagePrice).build();
    }
}
