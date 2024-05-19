package com.decportback.service;

import com.decportback.domain.stocks.Stocks;
import com.decportback.domain.stocks.StocksRepository;
import com.decportback.web.dto.StockSaveRequestDto;
import com.decportback.web.dto.StockResponseDto;
import com.decportback.web.dto.StockUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StocksService {

    private final StocksRepository stocksRepository;

    @Transactional
    public String save(StockSaveRequestDto dto) {
        return stocksRepository.save(dto.toEntity()).getTicker();
    }

    @Transactional
    public String update(String ticker, StockUpdateRequestDto dto) {
        Stocks stocks = stocksRepository.findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException("Entity does not exist. ticker = " + ticker));
        stocks.update(dto.getAmount(), dto.getAveragePrice());
        return ticker;
    }

    public StockResponseDto findById(String ticker) {
        Stocks entity = stocksRepository.findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException("Entity does not exist. ticker = " + ticker));
        return new StockResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<StockResponseDto> findAll() {
        return stocksRepository.findAll().stream()
                .map(StockResponseDto::new)
                .sorted(Comparator.comparing(StockResponseDto::getTicker))
                .collect(Collectors.toList());
    }
}
