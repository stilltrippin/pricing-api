package org.example.demotesttaskropa.infrastructure.persistence;

import org.example.demotesttaskropa.domain.model.Price;
import org.example.demotesttaskropa.domain.port.PriceRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository
                .findApplicablePrices(productId, brandId, date)
                .stream()
                .map(PriceEntity::toDomain)
                .toList();
    }
}
