package org.example.demotesttaskropa.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(Long id, Long brandId, Long productId, Integer priceList, Integer priority, BigDecimal price,
                    String curr, LocalDateTime startDate, LocalDateTime endDate) {
}
