package org.example.demotesttaskropa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException{
    public PriceNotFoundException(Long productId, Long priceId, LocalDateTime date) {
        super(String.format(
                "No price found for a Product: %d, Brand: %d, Date: %s ",
                productId, priceId, date));
    }
}
