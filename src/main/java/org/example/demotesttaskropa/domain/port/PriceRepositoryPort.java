package org.example.demotesttaskropa.domain.port;

import org.example.demotesttaskropa.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime date);
}
