package com.capitole.ecommerce.price.controller;

import com.capitole.ecommerce.price.exception.BadRequestException;
import com.capitole.ecommerce.price.exception.NotFoundException;
import com.capitole.ecommerce.price.model.brand.Brand;
import com.capitole.ecommerce.price.model.price.Currency;
import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.service.IPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.math.BigDecimal;
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

        return ResponseEntity.ok(PricesAPIResponse.fromPrice(price.get()));
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

    private static class PricesAPIResponse {
        private long priceList;
        private long product;
        private BigDecimal price;
        private long brand;
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;

        private PricesAPIResponse(long priceList, long product, BigDecimal price, long brand, ZonedDateTime startDate, ZonedDateTime endDate) {
            this.priceList = priceList;
            this.product = product;
            this.price = price;
            this.brand = brand;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        private PricesAPIResponse() {
        }

        public static PricesAPIResponse fromPrice(Price price) {
            return new PricesAPIResponse(price.getPriceList(), price.getProduct(), price.getPrice(), price.getBrand().getId(), price.getStartDate(), price.getEndDate());
        }

        public long getPriceList() {
            return priceList;
        }

        public long getProduct() {
            return product;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public long getBrand() {
            return brand;
        }

        public ZonedDateTime getStartDate() {
            return startDate;
        }

        public ZonedDateTime getEndDate() {
            return endDate;
        }
    }

}
