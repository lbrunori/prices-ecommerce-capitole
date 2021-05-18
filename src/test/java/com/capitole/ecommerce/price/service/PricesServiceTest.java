package com.capitole.ecommerce.price.service;

import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.repository.PricesRepository;
import com.capitole.ecommerce.price.service.impl.PricesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PricesServiceTest {

    @Autowired
    private PricesService pricesService;

    @MockBean
    private PricesRepository pricesRepository;

    private ZonedDateTime date = ZonedDateTime.of( 2021, 5,2,15,33,19, 0, ZoneId.of("America/Argentina/Buenos_Aires"));
    private int productId = 1;
    private int brandId = 1;

    @Test
    public void assertPricesControllerNotNull() {
        assertNotNull(pricesService);
    }

    @Test
    public void testOptionalIsEmptyWhenRepositoryResponseIsEmpty() {
        when(pricesRepository.findByStartDateAndBrandIdAndProductId(any(ZonedDateTime.class), anyInt(), anyInt()))
                .thenReturn(Collections.emptyList());
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(date, productId, brandId);
        assertTrue(price.isEmpty());
    }

    @Test
    public void testOptionalIsNotEmptyWhenThereIsOnePrice() {
        when(pricesRepository.findByStartDateAndBrandIdAndProductId(any(ZonedDateTime.class), anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(new Price()));
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(date, productId, brandId);
        assertFalse(price.isEmpty());
    }

    @Test
    public void testThatReturnsTheHighestPriorityValueWhenMoreThanOneObjectMatches() {
        Price priceMock1 = Mockito.mock(Price.class);
        Price priceMock2 = Mockito.mock(Price.class);
        Price priceMock3 = Mockito.mock(Price.class);

        when(priceMock1.getPriority()).thenReturn(1);
        when(priceMock2.getPriority()).thenReturn(2);
        when(priceMock3.getPriority()).thenReturn(3);

        when(pricesRepository.findByStartDateAndBrandIdAndProductId(any(ZonedDateTime.class), anyInt(), anyInt()))
                .thenReturn(Arrays.asList(priceMock1, priceMock2, priceMock3));
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(date, productId, brandId);

        verify(priceMock1, times(1)).getPriority();
        verify(priceMock2, times(2)).getPriority();
        verify(priceMock3, times(1)).getPriority();

        assertFalse(price.isEmpty());
        assertEquals(3, price.get().getPriority());

    }
}
