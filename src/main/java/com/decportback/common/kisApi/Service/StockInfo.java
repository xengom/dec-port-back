package com.decportback.common.kisApi.Service;

import com.decportback.common.kisApi.dto.KISTokenResponseDto;
import com.decportback.common.kisApi.dto.StockPriceMainDto;

public interface StockInfo {
    StockPriceMainDto getStockInfo(KISTokenResponseDto token, String appkey, String appsecret, String EXCD, String ticker);
}
