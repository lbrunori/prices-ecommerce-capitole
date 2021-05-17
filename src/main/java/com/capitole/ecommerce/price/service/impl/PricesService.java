package com.capitole.ecommerce.price.service.impl;

import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.service.IPricesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PricesService implements IPricesService {

    @Override
    public Optional<Price> getPriceByDateProductAndBrand() {
        return Optional.of(new Price());
    }
}
