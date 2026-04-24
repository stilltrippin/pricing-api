package org.example.demotesttaskropa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.demotesttaskropa.entity.Price;

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
                price.getBrandId(),
                price.getProductId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice());
    }
}
