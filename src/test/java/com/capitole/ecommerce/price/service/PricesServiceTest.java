package com.capitole.ecommerce.price.service;

import com.capitole.ecommerce.price.model.price.Price;
import com.capitole.ecommerce.price.repository.PricesRepository;
import com.capitole.ecommerce.price.service.impl.PricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PricesServiceTest {

    private PricesService pricesService;
    private PricesRepository pricesRepository;

    private ZonedDateTime date = ZonedDateTime.of( 2021, 5,2,15,33,19, 0, ZoneId.of("America/Argentina/Buenos_Aires"));
    private int productId = 1;
    private int brandId = 1;

    @BeforeEach
    public void beforeEach() {
        pricesRepository = Mockito.mock(PricesRepository.class);
        pricesService = new PricesService(pricesRepository);
    }

    @Test
    public void assertPricesControllerNotNull() {
        assertNotNull(pricesService);
    }

    @Test
    public void testOptionalIsEmptyWhenRepositoryResponseIsEmpty() {
        when(pricesRepository.findAllByStartDateBeforeAndEndDateAfterAndBrandIdAndProduct(any(ZonedDateTime.class), any(ZonedDateTime.class), anyLong(), anyLong()))
                .thenReturn(Collections.emptyList());
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(date, productId, brandId);
        assertTrue(!price.isPresent());
    }

    @Test
    public void testOptionalIsNotEmptyWhenThereIsOnePrice() {
        when(pricesRepository.findAllByStartDateBeforeAndEndDateAfterAndBrandIdAndProduct(any(ZonedDateTime.class), any(ZonedDateTime.class), anyLong(), anyLong()))
                .thenReturn(Collections.singletonList(new Price()));
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(date, productId, brandId);
        assertFalse(!price.isPresent());
    }

    @Test
    public void testThatReturnsTheHighestPriorityValueWhenMoreThanOneObjectMatches() {
        Price priceMock1 = Mockito.mock(Price.class);
        Price priceMock2 = Mockito.mock(Price.class);
        Price priceMock3 = Mockito.mock(Price.class);

        when(priceMock1.getPriority()).thenReturn(1);
        when(priceMock2.getPriority()).thenReturn(2);
        when(priceMock3.getPriority()).thenReturn(3);

        when(pricesRepository.findAllByStartDateBeforeAndEndDateAfterAndBrandIdAndProduct(any(ZonedDateTime.class), any(ZonedDateTime.class), anyLong(), anyLong()))
                .thenReturn(Arrays.asList(priceMock1, priceMock2, priceMock3));
        Optional<Price> price = pricesService.getPriceByDateProductAndBrand(date, productId, brandId);

        verify(priceMock1, times(1)).getPriority();
        verify(priceMock2, times(2)).getPriority();
        verify(priceMock3, times(1)).getPriority();

        assertFalse(!price.isPresent());
        assertEquals(3, price.get().getPriority());

    }
}
