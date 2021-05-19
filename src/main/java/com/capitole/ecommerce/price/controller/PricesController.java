package com.capitole.ecommerce.price.controller;

import com.capitole.ecommerce.price.exception.BadRequestException;
import com.capitole.ecommerce.price.exception.NotFoundException;
import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.service.IPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PricesController {

    private final IPricesService pricesService;

    @Autowired
    public PricesController(IPricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping
    public ResponseEntity getPriceByDateProductAndBrand(
            @RequestParam("application-date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime applicationDate,
            @RequestParam(name = "product-id", required = true) long productId,
            @RequestParam(name="brand-id", required = true) long brandId
    ) {

        validateParams(applicationDate, brandId, productId);

        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(applicationDate, brandId, productId);

        if (price.isEmpty()) {
            throw new NotFoundException("applicable_price_not_found", "Price was not found for the desired inputs");
        }

        return ResponseEntity.ok(price.get());
    }

    private void validateParams(ZonedDateTime applicationDate, long brandId, long productId) {
        if (brandId < 0) {
            throw new BadRequestException("invalid_brand_id", "Brand ID value can't be negative");
        }
        if (productId < 0) {
            throw new BadRequestException("invalid_product_id", "Product ID can't be negative");
        }
        ZonedDateTime fiveYearsOld = ZonedDateTime.now().minusYears(5);
        if (applicationDate.isBefore(fiveYearsOld)) {
            throw new BadRequestException("invalid_application_date", "Date is more than five years old");
        }
    }

}
