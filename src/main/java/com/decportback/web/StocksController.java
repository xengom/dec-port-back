package com.decportback.web;

import com.decportback.service.StocksService;
import com.decportback.web.dto.StockSaveRequestDto;
import com.decportback.web.dto.StockResponseDto;
import com.decportback.web.dto.StockUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {

    private final StocksService stocksService;

    @PostMapping("")
    public Long insertStock(@RequestBody StockSaveRequestDto dto) {
        return stocksService.save(dto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody StockUpdateRequestDto dto) {
        return stocksService.update(id, dto);
    }

    @GetMapping("/{id}")
    public StockResponseDto findById (@PathVariable Long id) {
        return stocksService.findById(id);
    }
}
