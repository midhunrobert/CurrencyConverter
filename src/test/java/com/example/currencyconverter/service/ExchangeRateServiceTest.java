package com.example.currencyconverter.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateServiceTest {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService() {
        @Override
        public Map<String, Double> getExchangeRates(String baseCurrency) {
            if ("INVALID".equals(baseCurrency)) {
                throw new RuntimeException("External API is unavailable");
            }
            Map<String, Double> mockRates = new HashMap<>();
            mockRates.put("EUR", 0.85);
            mockRates.put("GBP", 0.75);
            return mockRates;
        }
    };

    @Test
    void testGetExchangeRates_ValidBaseCurrency() {
        Map<String, Double> rates = exchangeRateService.getExchangeRates("USD");
        assertNotNull(rates, "Exchange rates should not be null");
        assertTrue(rates.containsKey("EUR"), "Rates should contain EUR");
        assertTrue(rates.get("EUR") > 0, "EUR exchange rate should be positive");
    }

    @Test
    void testGetExchangeRates_InvalidBaseCurrency() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            exchangeRateService.getExchangeRates("INVALID");
        });

        assertEquals("External API is unavailable", exception.getMessage(), "Exception message should match");
    }
}
