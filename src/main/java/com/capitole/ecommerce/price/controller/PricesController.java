package com.capitole.ecommerce.price.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/prices")
public class PricesController {

    @GetMapping
    public ResponseEntity<?> getPriceByDateProductAndBrand() {
        return null;
    }
}
