package com.decportback.kisApi.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StockPriceRequestDto {
    private String countryCode;
    private String exchangeMarket;
    private String ticker;
}
