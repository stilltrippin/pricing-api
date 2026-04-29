package org.example.demotesttaskropa.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.demotesttaskropa.domain.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse (
    Long brandId,
    Long productId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    BigDecimal price
) {
    public PriceResponse(Price price) {
        this(
                price.brandId(),
                price.productId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.price());
    }
}
