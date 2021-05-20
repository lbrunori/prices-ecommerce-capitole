package com.capitole.ecommerce.price.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PricesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNotFoundSuitablePriceApplicable() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-17T00:00:00Z&product-id=1&brand-id=1")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("applicable_price_not_found")));
    }

    @Test
    public void testBrandIdHasInvalidValue() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-17T00:00:00Z&product-id=1&brand-id=-1")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("invalid_brand_id")));
    }

    @Test
    public void testProductIdHasInvalidValue() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-17T00:00:00Z&product-id=-11&brand-id=1")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("invalid_product_id")));
    }

    @Test
    public void testDateIsMoreThanFiveYearsOld() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2010-06-17T00:00:00Z&product-id=1&brand-id=1")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("invalid_application_date")));
    }

    @Test
    public void testBrandZaraAndProduct35455AndDateMay14At10ResultWithOneResult() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-14T10:00:00-03:00&product-id=35455&brand-id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"priceList\":1")));
    }

    @Test
    public void testBrandZaraAndProduct35455AndDateMay14At16ResultWithOneResult() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-14T16:00:00-03:00&product-id=35455&brand-id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"priceList\":2")));
    }

    @Test
    public void testBrandZaraAndProduct35455AndDateMay14At21ResultWithOneResult() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-14T21:00:00-03:00&product-id=35455&brand-id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"priceList\":1")));
    }


    @Test
    public void testBrandZaraAndProduct35455AndDateMay15At10ResultWithOneResult() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-15T10:00:00-03:00&product-id=35455&brand-id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"priceList\":3")));
    }

    @Test
    public void testBrandZaraAndProduct35455AndDateMay21At10ResultWithOneResult() throws Exception {
        this.mockMvc.perform(get("/api/prices?application-date=2020-06-16T21:00:00-03:00&product-id=35455&brand-id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"priceList\":4")));
    }


}
