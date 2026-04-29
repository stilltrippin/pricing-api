package org.example.demotesttaskropa.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException{
    public PriceNotFoundException(Long productId, Long priceId, LocalDateTime date) {
        super(String.format(
                "No price found for a Product: %d, Brand: %d, Date: %s ",
                productId, priceId, date));
    }
}
