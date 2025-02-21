package com.example.currencyconverter.controller;

import com.example.currencyconverter.model.CurrencyConversionResponse;
import com.example.currencyconverter.model.CurrencyConversionRequest;
import com.example.currencyconverter.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyConverterController {

    private final ExchangeRateService exchangeRateService;

    public CurrencyConverterController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/rates")
    public ResponseEntity<Map<String, Double>> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        try {
            return ResponseEntity.ok(exchangeRateService.getExchangeRates(base));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        try {
            Map<String, Double> rates = exchangeRateService.getExchangeRates(request.getFrom());

            if (!rates.containsKey(request.getTo())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid currency code"));
            }

            if (request.getAmount() <= 0) {
                return ResponseEntity.badRequest().body(Map.of("error", "Amount must be positive"));
            }

            double convertedAmount = request.getAmount() * rates.get(request.getTo());
            CurrencyConversionResponse response = new CurrencyConversionResponse(
                    request.getFrom(), request.getTo(), request.getAmount(), convertedAmount
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
