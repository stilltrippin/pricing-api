package org.example.demotesttaskropa.domain.port;

import org.example.demotesttaskropa.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findTopPriorityPrice(Long productId, Long brandId, LocalDateTime date);
}
