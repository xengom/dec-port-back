package com.decportback.web.dto;

import com.decportback.domain.stocks.Stocks;
import lombok.Getter;

@Getter
public class StockResponseDto {
    private Long id;
    private String equityAccount;
    private String countryCode;
    private String exchangeMarket;
    private String ticker;
    private String stockName;
    private Integer amount;
    private Double averagePrice;

    public StockResponseDto(Stocks entity) {
        this.id = entity.getId();
        this.equityAccount = entity.getEquityAccount();
        this.countryCode = entity.getCountryCode();
        this.exchangeMarket = entity.getExchangeMarket();
        this.ticker = entity.getTicker();
        this.stockName = entity.getStockName();
        this.amount = entity.getAmount();
        this.averagePrice = entity.getAveragePrice();
    }
}
