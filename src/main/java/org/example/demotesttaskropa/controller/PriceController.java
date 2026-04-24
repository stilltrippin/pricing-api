package org.example.demotesttaskropa.controller;

import org.example.demotesttaskropa.dto.PriceResponse;
import org.example.demotesttaskropa.entity.Price;
import org.example.demotesttaskropa.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private  final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public PriceResponse getPrice(
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ){
        Price price = priceService.resolvePrice(productId, brandId, date);
        return new PriceResponse(price);
    }

}
