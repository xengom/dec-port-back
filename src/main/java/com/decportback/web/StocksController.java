package com.decportback.web;

import com.decportback.service.StocksService;
import com.decportback.web.dto.StockSaveRequestDto;
import com.decportback.web.dto.StockResponseDto;
import com.decportback.web.dto.StockUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {

    private final StocksService stocksService;

    @PostMapping("")
    public String insertStock(@RequestBody StockSaveRequestDto dto) {
        return stocksService.save(dto);
    }

    @PutMapping("/{ticker}")
    public String update(@PathVariable String ticker, @RequestBody StockUpdateRequestDto dto) {
        return stocksService.update(ticker, dto);
    }

    @GetMapping("/{ticker}")
    public StockResponseDto findById (@PathVariable String ticker) {
        return stocksService.findById(ticker);
    }

    @GetMapping("")
    public List<StockResponseDto> findAll() {
        return stocksService.findAll();
    }
}
