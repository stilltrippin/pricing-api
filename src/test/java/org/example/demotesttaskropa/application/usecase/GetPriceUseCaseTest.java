package org.example.demotesttaskropa.application.usecase;

import org.example.demotesttaskropa.domain.model.Price;
import org.example.demotesttaskropa.domain.port.PriceRepositoryPort;
import org.example.demotesttaskropa.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;
    private static final LocalDateTime DATE = LocalDateTime.of(2020, 6, 14, 10, 0);

    private Price samplePrice;

    @BeforeEach
    void setUp() {
        samplePrice = new Price(
                1L, BRAND_ID, PRODUCT_ID, 1, 0,
                new BigDecimal("35.50"), "EUR",
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59)
        );
    }

    @Test
    @DisplayName("Returns price when repository finds a match")
    void execute_returnsPriceWhenFound() {
        when(priceRepositoryPort.findTopPriorityPrice(PRODUCT_ID, BRAND_ID, DATE))
                .thenReturn(Optional.of(samplePrice));

        Price result = getPriceUseCase.execute(PRODUCT_ID, BRAND_ID, DATE);

        assertThat(result).isNotNull();
        assertThat(result.productId()).isEqualTo(PRODUCT_ID);
        assertThat(result.brandId()).isEqualTo(BRAND_ID);
        assertThat(result.price()).isEqualByComparingTo("35.50");
    }

    @Test
    @DisplayName("Throws PriceNotFoundException when no price matches")
    void execute_throwsWhenNotFound() {
        when(priceRepositoryPort.findTopPriorityPrice(PRODUCT_ID, BRAND_ID, DATE))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> getPriceUseCase.execute(PRODUCT_ID, BRAND_ID, DATE))
                .isInstanceOf(PriceNotFoundException.class);
    }

    @Test
    @DisplayName("Calls repository exactly once with correct arguments")
    void execute_callsRepositoryOnce() {
        when(priceRepositoryPort.findTopPriorityPrice(PRODUCT_ID, BRAND_ID, DATE))
                .thenReturn(Optional.of(samplePrice));

        getPriceUseCase.execute(PRODUCT_ID, BRAND_ID, DATE);

        verify(priceRepositoryPort, times(1))
                .findTopPriorityPrice(PRODUCT_ID, BRAND_ID, DATE);
    }
}
