package org.example.demotesttaskropa.application.usecase;

import org.example.demotesttaskropa.domain.model.Price;
import org.example.demotesttaskropa.domain.port.PriceRepositoryPort;
import org.example.demotesttaskropa.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price execute(Long productId, Long brandId, LocalDateTime date) {
       return priceRepositoryPort.findApplicablePrices(productId, brandId, date)
               .stream()
               .max(Comparator.comparing(Price::priority))
               .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
}
