package com.capitole.ecommerce.price.model.price;

import com.capitole.ecommerce.price.model.brand.Brand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_list", nullable = false)
    private long priceList;
    @Column(name = "start_date", nullable = false)
    private final ZonedDateTime startDate = ZonedDateTime.now();
    @Column(name = "end_date", nullable = false)
    private final ZonedDateTime endDate = ZonedDateTime.now();
    @Column(name = "priority", nullable = false)
    private int priority;
    @Column(name = "product_id", nullable = false)
    private long product;
    @Column(name = "price", nullable = true)
    private BigDecimal price;
    @ManyToOne
    private Brand brand;
    @Enumerated(EnumType.STRING)
    @Column(name = "curr")
    private Currency currency;

    public Price(int priceList, int priority, Brand brand, Currency currency) {
        this.priceList = priceList;
        this.priority = priority;
        this.brand = brand;
        this.currency = currency;
    }

    public Price() {
    }

    public long getPriceList() {
        return priceList;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public int getPriority() {
        return priority;
    }

    public long getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }
}
