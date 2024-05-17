package com.decportback.kisApi.dto;

import lombok.Getter;

@Getter
public class StockPriceResponseDto {
    // kr stock
    // 주식현재가
    private String stck_prpr;
    // 전일 대비
    private String prdy_vrss;
    // 전일 대비율
    private String prdy_ctrt;

    // us stock
    // 주식현재가
    private String last;
    // 전일 대비
    private String diff;
    // 전일 대비율
    private String rate;
}
