package com.example.converter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "CurrencyClient", url = "${feign.client.currency-client-url}")
public interface CurrencyClient {

    @RequestMapping(value = "/latest.json?app_id=${key.CURRENCY_API_KEY}", method = RequestMethod.GET)
    ResponseEntity<CurrencyDTO> getLatestCurrencyValue(
            @RequestParam String base
    );


    @RequestMapping(value = "/historical/{date}.json?app_id=${key.CURRENCY_API_KEY}",method = RequestMethod.GET)
    ResponseEntity<CurrencyDTO> getPastCurrencyValue(
            @PathVariable(value = "date", required = true) String date,
            @RequestParam String base
    );

}
