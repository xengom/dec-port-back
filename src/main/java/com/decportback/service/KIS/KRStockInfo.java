package com.decportback.service.KIS;

import com.decportback.common.HttpHeader;
import com.decportback.common.RestProvider;
import com.decportback.web.dto.StockPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class KRStockInfo implements StockInfo{

    private final RestProvider restProvider;

    @Override
    public StockPriceDto getStockInfo(String token, String domain, String ticker) {
        String krDomain = "/uapi/domestic-stock/v1/quotations/inquire-price";

        // request header
        Map<String, String> header = new HashMap<>();
        header.put("authorization", token);
        HttpHeader httpHeader = new HttpHeader(MediaType.APPLICATION_JSON);
        httpHeader.addHeader(header);


        // request body
        Map<String, String> params = new HashMap<>();
        params.put("fid_cond_mrkt_div_code", "J");
        params.put("fid_input_iscd", ticker);

        ResponseEntity<StockPriceDto> response = restProvider.send(HttpMethod.GET, domain+krDomain, params, httpHeader, StockPriceDto.class);

        return response.getBody();
    }

}
