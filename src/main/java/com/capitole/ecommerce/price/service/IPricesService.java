package com.capitole.ecommerce.price.service;

import com.capitole.ecommerce.price.model.price.Price;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface IPricesService {

    Optional<Price> getPriceByDateProductAndBrand(ZonedDateTime date, long brandId, long productId);
}
