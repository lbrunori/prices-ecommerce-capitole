package com.capitole.ecommerce.price.controller;

import com.capitole.ecommerce.price.exception.NotFoundException;
import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.service.IPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController("/prices")
public class PricesController {

    private final IPricesService pricesService;

    @Autowired
    public PricesController(IPricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping
    public ResponseEntity getPriceByDateProductAndBrand(
            @RequestParam("application-date") ZonedDateTime applicationDate,
            @RequestParam("product-id") Integer productId,
            @RequestParam("brand-id") Integer brandId

    ) {
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand();

        if (!price.isPresent()) {
            throw new NotFoundException("Price was not found for the desired inputs");
        }

        return ResponseEntity.ok(pricesService.getPriceByDateProductAndBrand());
    }

    private boolean isOldDate(ZonedDateTime zonedDateTime) {
        return false;
    }

}
