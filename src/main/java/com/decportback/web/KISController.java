package com.decportback.web;

import com.decportback.service.KIS.KISService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KISController {

    private final KISService kisService;

    @GetMapping("/")
    public String getStockInfo() {
        kisService.getToken();
        kisService.getKRStockPrice("005930");
        return "";
    }
}
