package org.example.demotesttaskropa.application.usecase;

import org.example.demotesttaskropa.domain.model.Price;
import org.example.demotesttaskropa.domain.port.PriceRepositoryPort;
import org.example.demotesttaskropa.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price execute(Long productId, Long brandId, LocalDateTime date) {
        return priceRepositoryPort.findTopPriorityPrice(productId, brandId, date)
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
}
