package com.decportback.web.dto;

import lombok.Getter;

@Getter
public class StockPriceDto extends KISCommonDto {

    // 한국주식
    // 주식현재가
    private String stck_prpr;
    // 전일 대비
    private String prdy_vrss;
    // 전일 대비율
    private String prdy_ctrt;

    // 미국주식
    // 주식현재가
    private String last;
    // 전일 대비
    private String diff;
    // 전일 대비율
    private String rate;

}
