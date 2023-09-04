package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class CurrencyConverterController {

    private static final String API_KEY = "bdb32c47e0cb4f938337166ebf4644ff";
    private static final String BASE_URL = "https://openexchangerates.org/api/latest.json";

    @GetMapping("/currency-converter")
    public String converterForm() {
        return "converter";
    }

    @PostMapping("/convert")
    public String convertCurrency(
            @RequestParam("fromCurrency") String fromCurrency,
            @RequestParam("toCurrency") String toCurrency,
            @RequestParam("amount") double amount,
            Model model) {

        String url = BASE_URL + "?app_id=" + API_KEY + "&base=" + fromCurrency;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrencyConversionResponse> responseEntity = restTemplate.getForEntity(url, CurrencyConversionResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            CurrencyConversionResponse response = responseEntity.getBody();
            if (response != null && response.getRates().containsKey(toCurrency)) {
                double conversionRate = response.getRates().get(toCurrency);
                double convertedAmount = amount * conversionRate;

                model.addAttribute("fromCurrency", fromCurrency);
                model.addAttribute("toCurrency", toCurrency);
                model.addAttribute("amount", amount);
                model.addAttribute("convertedAmount", convertedAmount);

                return "conversionResult";
            }
        }

        model.addAttribute("error", "Не удалось получить курс обмена.");
        return "converter";
    }
}
