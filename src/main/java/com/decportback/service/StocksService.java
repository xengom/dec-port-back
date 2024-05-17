package com.decportback.service;

import com.decportback.domain.StocksRepository;
import com.decportback.web.dto.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StocksService {

    private final StocksRepository stocksRepository;

    @Transactional
    public Long save(StockSaveRequestDto dto) {
        return stocksRepository.save(dto.toEntity()).getId();
    }
}
