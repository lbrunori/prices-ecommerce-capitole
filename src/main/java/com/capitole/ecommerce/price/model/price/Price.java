package com.capitole.ecommerce.price.model.price;

import com.capitole.ecommerce.price.model.brand.Brand;

import java.time.ZonedDateTime;

public class Price {
    private final ZonedDateTime startDate = ZonedDateTime.now();
    private final ZonedDateTime endDate = ZonedDateTime.now();
    private int priceList;
    private int priority;
    private Brand brand;
    private Currency currency;

    public Price(int priceList, int priority, Brand brand, Currency currency) {
        this.priceList = priceList;
        this.priority = priority;
        this.brand = brand;
        this.currency = currency;
    }

    public Price() {
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public int getPriceList() {
        return priceList;
    }

    public int getPriority() {
        return priority;
    }

    public Brand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }
}
