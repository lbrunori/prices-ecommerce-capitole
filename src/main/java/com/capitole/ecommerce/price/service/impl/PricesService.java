package com.capitole.ecommerce.price.service.impl;

import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.repository.PricesRepository;
import com.capitole.ecommerce.price.service.IPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService implements IPricesService {

    private final PricesRepository pricesRepository;

    @Autowired
    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public Optional<Price> getPriceByDateProductAndBrand(ZonedDateTime date, long brandId, long productId) {
        List<Price> prices = pricesRepository.findAllByStartDateBeforeAndEndDateAfterAndBrandIdAndProduct(date, date, brandId, productId);

        return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority));
    }
}
