package com.capitole.ecommerce.price.controller;

import com.capitole.ecommerce.price.exception.NotFoundException;
import com.capitole.ecommerce.price.model.brand.Brand;
import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.service.IPricesService;
import com.capitole.ecommerce.price.service.PricesServiceTest;
import com.capitole.ecommerce.price.service.impl.PricesService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PricesControllerTest {

    private PricesController pricesController;
    private IPricesService pricesService;

    private ZonedDateTime date = ZonedDateTime.of(2021, 5, 2, 15, 33, 19, 0, ZoneId.of("America/Argentina/Buenos_Aires"));
    private long productId = 1;
    private long brandId = 1;

    @BeforeEach
    public void beforeAll() {
        pricesService = Mockito.mock(PricesService.class);
        pricesController = new PricesController(pricesService);
    }

    @Test
    public void pricesControllerIsNotNull() {
        assertNotNull(pricesController);
    }

    @Test
    public void controllerReturnsOneResult() {
        Price price = Mockito.mock(Price.class);
        Brand brand = Mockito.mock(Brand.class);
        when(price.getBrand()).thenReturn(brand);
        when(brand.getId()).thenReturn(1L);

        when(pricesService.getPriceByDateProductAndBrand(any(ZonedDateTime.class), anyLong(), anyLong())).thenReturn(Optional.of(price));

        assertEquals(200, pricesController.getPriceByDateProductAndBrand(date, productId, brandId).getStatusCodeValue());
        assertNotNull(pricesController.getPriceByDateProductAndBrand(date, productId, brandId).getBody());
    }

    @Test
    public void controllerReturnsEmptyResponseAndThrowsNotFoundException() {
        when(pricesService.getPriceByDateProductAndBrand(any(ZonedDateTime.class), anyLong(), anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> pricesController.getPriceByDateProductAndBrand(date, productId, brandId));
    }
}
