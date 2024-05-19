package com.decportback.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockUpdateRequestDto {

    private Integer amount;
    private Double averagePrice;

    @Builder
    public StockUpdateRequestDto(
            Integer amount,
            Double averagePrice) {
        this.amount = amount;
        this.averagePrice = averagePrice;
    }
}
