package com.decportback.domain.stocks;

import com.decportback.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Stocks extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equity_account", length = 20, nullable = false)
    private String equityAccount;

    @Column(name = "country_code", length = 2, nullable = false)
    private String countryCode;

    @Column(name = "exchange_market", length = 3)
    private String exchangeMarket;

    @Column(length = 10, nullable = false)
    private String ticker;

    @Column(name = "stock_name", nullable = false)
    private String stockName;

    private Integer amount;

    private Double averagePrice;

    @Builder
    public Stocks(String equityAccount,
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

    public void update(Integer amount, Double averagePrice) {
        this.amount = amount;
        this.averagePrice = averagePrice;
    }
}
