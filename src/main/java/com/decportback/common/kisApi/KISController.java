package com.decportback.common.kisApi;

import com.decportback.common.kisApi.Service.KISMainService;
import com.decportback.common.kisApi.dto.StockPriceMainDto;
import com.decportback.common.kisApi.dto.StockPriceRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stockInfo")
public class KISController {

    private final KISMainService kisMainService;

    @GetMapping("/kr")
    public StockPriceMainDto getKoreaStockInfo() {
        // TODO @RequestParam으로 변경
        StockPriceRequestDto dto = StockPriceRequestDto.builder()
                .countryCode("KR")
                .exchangeMarket("")
                .ticker("005930")
                .build();
        return kisMainService.getStockPrice(dto.getCountryCode(),dto.getExchangeMarket(), dto.getTicker());
    }

    @GetMapping("/us")
    public StockPriceMainDto getUsStockInfo() {
        // TODO @RequestParam으로 변경
        StockPriceRequestDto dto = StockPriceRequestDto.builder()
                .countryCode("US")
                .exchangeMarket("NAS")
                .ticker("AAPL")
                .build();
        return kisMainService.getStockPrice(dto.getCountryCode(),dto.getExchangeMarket(), dto.getTicker());
    }
}
