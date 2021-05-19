package com.capitole.ecommerce.price.repository;

import com.capitole.ecommerce.price.model.price.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {

    List<Price> findAll();

    List<Price> findByStartDateBeforeAndEndDateAfterAndBrandIdAndProduct(ZonedDateTime date1, ZonedDateTime date2, long brandId, long productId);
}
