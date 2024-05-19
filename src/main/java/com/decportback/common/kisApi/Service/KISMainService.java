package com.decportback.common.kisApi.Service;

import com.decportback.common.kisApi.dto.KISTokenRequestDto;
import com.decportback.common.kisApi.apiRequest.KISRestAPI;
import com.decportback.common.kisApi.apiRequest.RetrofitUtils;
import com.decportback.common.kisApi.dto.KISTokenResponseDto;
import com.decportback.common.kisApi.dto.StockPriceMainDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import retrofit2.Call;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Service Logic for get Stock Info via KIS
 */

@Service
@RequiredArgsConstructor
@PropertySource("classpath:config.properties")
public class KISMainService {

    // REST Utils
    private final KISRestAPI kisRestAPI;
    private final RetrofitUtils retrofitUtils;

    // StockInfo Services
    private final KRStockInfo krStockInfo;
    private final USStockInfo usStockInfo;

    // Parameter for get Token (API KEY Included)
    @Value("${GRANT_TYPE}")
    private String grant_type;
    @Value("${APP_KEY}")
    private String appkey;
    @Value("${APP_SECRET}")
    private String appsecret;

    // Token Info
    private KISTokenResponseDto TOKEN_OBJECT;

    /**
     * @author dec
     * @since 240518
     * @Description check token is valid
     */
    public void isTokenExpired() {
        // TODO LocalTest 걷어내기
        boolean isLocal = true;
        if (ObjectUtils.isEmpty(TOKEN_OBJECT)) {
            getToken(isLocal);
        } else {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate tokenExpiredDate = LocalDate.parse(TOKEN_OBJECT.getAccess_token_token_expired(), formatter);
            if(now.isAfter(tokenExpiredDate)) {
                getToken(isLocal);
            }
        }
    }

    /**
     * @author dec
     * @since 240516
     * @Description get token for access KIS apis
     */
    public void getToken(boolean isLocalTest) {
        // TODO LocalTest 걷어내기
        if (isLocalTest) {
            TOKEN_OBJECT = KISTokenResponseDto.builder()
                    .access_token_token_expired("2024-05-20 21:15:36")
                    .access_token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6Ijk3ZDZkOWQxLTE5ZmMtNDFiZC05OTM4LTkzOTVjNTk1MGFkMCIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTcxNjIwNzMzNiwiaWF0IjoxNzE2MTIwOTM2LCJqdGkiOiJQU2JNbHB4Mzl0YjdxRWJQeUxlY2FVZGVLek9KdHlXRElSalkifQ.YvzVq5H_OoW-gEV3Qkvg7czYLvNjy6GIubbRaJO2-c-cvSPvnZnWchw1ezO47PLDdEH82JbeuUujuJYiIO_oBw")
                    .token_type("Bearer")
                    .expires_in(86400)
                    .build();
        } else {
            KISTokenRequestDto requestDto = KISTokenRequestDto.builder()
                    .grant_type(grant_type)
                    .appkey(appkey)
                    .appsecret(appsecret).build();
            Call<KISTokenResponseDto> call = kisRestAPI.getToken(requestDto);
            TOKEN_OBJECT = retrofitUtils.execute(call);
        }
    }

    /**
     * @author dec
     * @since 240517
     * @Description get stock price from KIS
     */
    public StockPriceMainDto getStockPrice(String countryCode, String exchangeMarket, String ticker) {
        isTokenExpired();
        return countryCode.equals("KR") ?
        krStockInfo.getStockInfo(TOKEN_OBJECT, appkey, appsecret, "", ticker) :
                usStockInfo.getStockInfo(TOKEN_OBJECT, appkey, appsecret, exchangeMarket, ticker);
    }
}