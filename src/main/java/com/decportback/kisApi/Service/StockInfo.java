package com.decportback.kisApi.Service;

import com.decportback.kisApi.dto.KISTokenResponseDto;
import com.decportback.kisApi.dto.StockPriceMainDto;

public interface StockInfo {
    StockPriceMainDto getStockInfo(KISTokenResponseDto token, String appkey, String appsecret, String EXCD, String ticker);
}
