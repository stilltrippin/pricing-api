package org.example.demotesttaskropa.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
        select p from PriceEntity p
        where p.productId= :productId
            and p.brandId= :brandId
            and :targetDate between p.startDate and p.endDate
        order by p.priority desc
        limit 1
    """)
    Optional<PriceEntity> findTopPriorityPrice(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("targetDate") LocalDateTime targetDate);
}
