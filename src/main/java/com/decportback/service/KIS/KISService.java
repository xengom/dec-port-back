package com.decportback.service.KIS;

import com.decportback.common.RestProvider;
import com.decportback.web.dto.KISTokenDto;
import com.decportback.web.dto.StockPriceDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Service Logic for get Stock Info via KIS
 */

@Service
@RequiredArgsConstructor
@PropertySource("classpath:config.properties")
public class KISService {

    private static final Logger log = LoggerFactory.getLogger(KISService.class);

    private final RestProvider restProvider;

    private final KRStockInfo krStockInfo;

    // API DOMAIN
    private final String MAIN_DOMAIN = "https://openapi.koreainvestment.com:9443";

    // Parameter for get Token
    @Value("${GRANT_TYPE}")
    private String grant_type;
    @Value("${APP_KEY}")
    private String appkey;
    @Value("${APP_SECRET}")
    private String appsecret;

    private String token;

    /**
     * @author dec
     * @since 240516
     * @return String accessToken
     */
    @Scheduled(cron = "0 0 8 * * *")
    public void getToken() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", grant_type);
        params.put("appkey", appkey);
        params.put("appsecret", appsecret);
        ResponseEntity<KISTokenDto> response = restProvider.send(HttpMethod.POST, MAIN_DOMAIN + "/oauth2/tokenP", params, KISTokenDto.class);
        token = Objects.requireNonNull(response.getBody()).getAccess_token();
    }

    public void getKRStockPrice(String ticker) {
        StockPriceDto stockPriceDto = krStockInfo.getStockInfo(token, MAIN_DOMAIN, ticker);
        System.out.println("sto = " + stockPriceDto.getStck_prpr());
    }
}
