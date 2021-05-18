package com.capitole.ecommerce.price.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PricesRepositoryTest {

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    public void assertPricesRepositoryIsNotNull() {
        assertNotNull(pricesRepository);
    }
}
