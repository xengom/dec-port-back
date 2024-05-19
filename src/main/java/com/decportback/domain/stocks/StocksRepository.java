package com.decportback.domain.stocks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepository extends JpaRepository<Stocks, String> {
}
