package com.decportback.service.KIS;

import com.decportback.web.dto.StockPriceDto;

public interface StockInfo {
    StockPriceDto getStockInfo(String token, String domain, String ticker);

}
