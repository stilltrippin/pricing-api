package org.example.demotesttaskropa.service;

import org.example.demotesttaskropa.entity.Price;
import org.example.demotesttaskropa.exception.PriceNotFoundException;
import org.example.demotesttaskropa.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price resolvePrice( Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findTopPriorityPrice(productId, brandId, date)
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
}
