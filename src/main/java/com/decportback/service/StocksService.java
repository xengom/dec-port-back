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
    public Long save(StockSaveRequestDto dto) {
        return stocksRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, StockUpdateRequestDto dto) {
        Stocks stocks = stocksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity does not exist. id = " + id));
        stocks.update(dto.getAmount(), dto.getAveragePrice());
        return id;
    }

    public StockResponseDto findById(Long id) {
        Stocks entity = stocksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity does not exist. id = " + id));
        return new StockResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<StockResponseDto> findAll() {
        return stocksRepository.findAll().stream()
                .map(StockResponseDto::new)
                .sorted(Comparator.comparing(StockResponseDto::getId))
                .collect(Collectors.toList());
    }
}
