package com.decportback.common.kisApi.Service;

import com.decportback.common.kisApi.apiRequest.KISRestAPI;
import com.decportback.common.kisApi.apiRequest.RetrofitUtils;
import com.decportback.common.kisApi.dto.KISTokenResponseDto;
import com.decportback.common.kisApi.dto.StockPriceMainDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Call;

@RequiredArgsConstructor
@Component
public class KRStockInfo implements StockInfo{

    private final KISRestAPI kisRestAPI;
    private final RetrofitUtils retrofitUtils;

    @Override
    public StockPriceMainDto getStockInfo(KISTokenResponseDto token, String appkey, String appsecret, String EXCD, String ticker) {
        String authToken = "Bearer " + token.getAccess_token();
        Call<StockPriceMainDto> call = kisRestAPI.getKRStockPrice(authToken, appkey, appsecret, "FHKST01010100", "J", ticker);
        return retrofitUtils.execute(call);
    }
}
