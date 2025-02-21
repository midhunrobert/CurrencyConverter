package com.example.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public Map<String, Double> getExchangeRates(String baseCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = UriComponentsBuilder.fromHttpUrl(API_URL + baseCurrency).toUriString();
            Map response = restTemplate.getForObject(url, Map.class);
            Map<String, Object> rawRates = (Map<String, Object>) response.get("rates");
            Map<String, Double> rates = new HashMap<>();

            for (Map.Entry<String, Object> entry : rawRates.entrySet()) {
                rates.put(entry.getKey(), ((Number) entry.getValue()).doubleValue());
            }

            return rates;
        } catch (Exception e) {
            throw new RuntimeException("External API is unavailable");
        }
    }
}
