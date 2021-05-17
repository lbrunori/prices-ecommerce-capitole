package com.capitole.ecommerce.price.service;

import com.capitole.ecommerce.price.service.impl.PricesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PricesServiceTest {

    @Autowired
    private PricesService pricesService;

    @Test
    public void assertPricesControllerNotNull() {
        assertNotNull(pricesService);
    }
}
