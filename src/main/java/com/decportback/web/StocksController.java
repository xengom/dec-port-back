package com.decportback.web;

import com.decportback.service.StocksService;
import com.decportback.web.dto.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StocksController {

    private final StocksService stocksService;

    @PostMapping("/api/v1/stocks")
    public Long insertStock(@RequestBody StockSaveRequestDto dto) {
        return stocksService.save(dto);
    }
}
