package com.capitole.ecommerce.price.controller;

import com.capitole.ecommerce.price.exception.NotFoundException;
import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.service.IPricesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PricesControllerTest {

    @Autowired
    private PricesController pricesController;
    @MockBean
    private IPricesService pricesService;

    private ZonedDateTime date = ZonedDateTime.of( 2021, 5,2,15,33,19, 0, ZoneId.of("America/Argentina/Buenos_Aires"));
    private int productId = 1;
    private int brandId = 1;

    @Test
    public void pricesControllerIsNotNull() {
        assertNotNull(pricesController);
    }

    @Test
    public void controllerReturnsOneResult() {
        when(pricesService.getPriceByDateProductAndBrand(any(ZonedDateTime.class), anyInt(), anyInt())).thenReturn(Optional.of(new Price()));

        assertEquals(200, pricesController.getPriceByDateProductAndBrand(date, productId, brandId).getStatusCodeValue());
        assertNotNull(pricesController.getPriceByDateProductAndBrand(date, productId, brandId).getBody());
    }

     @Test
    public void controllerReturnsEmptyRepsonseAndThrowsNotFoundException(){
         when(pricesService.getPriceByDateProductAndBrand(any(ZonedDateTime.class), anyInt(), anyInt())).thenReturn(Optional.empty());

         assertThrows(NotFoundException.class, () -> pricesController.getPriceByDateProductAndBrand(date, productId, brandId));
     }
}
