package com.decportback.common.kisApi.apiRequest;

import com.decportback.common.kisApi.dto.KISTokenRequestDto;
import com.decportback.common.kisApi.dto.KISTokenResponseDto;
import com.decportback.common.kisApi.dto.StockPriceMainDto;
import retrofit2.Call;
import retrofit2.http.*;

public interface KISRestAPI {

    @POST("/oauth2/tokenP")
    Call<KISTokenResponseDto> getToken(@Body KISTokenRequestDto body);

    @GET("/uapi/domestic-stock/v1/quotations/inquire-price")
    Call<StockPriceMainDto> getKRStockPrice(
            @Header("Authorization") String token,
            @Header("appkey") String appkey,
            @Header("appsecret") String appsecret,
            @Header("tr_id") String tr_id,
            @Query("FID_COND_MRKT_DIV_CODE") String FID_COND_MRKT_DIV_CODE,
            @Query("FID_INPUT_ISCD") String FID_INPUT_ISCD);

    @GET("/uapi/overseas-price/v1/quotations/price")
    Call<StockPriceMainDto> getUSStockPrice(
            @Header("Authorization") String token,
            @Header("appkey") String appkey,
            @Header("appsecret") String appsecret,
            @Header("tr_id") String tr_id,
            @Query("AUTH") String AUTH,
            @Query("EXCD") String EXCD,
            @Query("SYMB") String SYMB);

}
