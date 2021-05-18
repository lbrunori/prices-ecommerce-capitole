package com.capitole.ecommerce.price.repository;

import com.capitole.ecommerce.price.model.price.Price;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class PricesRepository {

    public List<Price> findByStartDateAndBrandIdAndProductId(ZonedDateTime date, int brandId, int productId) {
        return Collections.emptyList();
    };
}
