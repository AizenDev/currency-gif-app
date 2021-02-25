package com.example.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ConverterService {
    private final GifClient gifClient;

    private final CurrencyClient currencyClient;

    @Autowired
    public ConverterService(GifClient gifClient, CurrencyClient currencyClient) {
        this.gifClient = gifClient;
        this.currencyClient = currencyClient;
    }

    public Map<String, String> convertCurrency(String base) {
       CurrencyDTO latest = currencyClient.getLatestCurrencyValue(base).getBody();
       CurrencyDTO historical = currencyClient.getPastCurrencyValue(this.getStringDate(), base).getBody();

       String tag;

        assert latest != null;
        String latestVal =  latest.getRates().get("RUB");
        assert historical != null;
        String historicalVal = historical.getRates().get("RUB");

       double latest_value = Double.parseDouble(latestVal);
       double historical_value = Double.parseDouble(historicalVal);

       if (latest_value > historical_value) {
           tag = "rich";
       } else {
           tag = "broke";
       }


        GifDTO gif = gifClient.getGifByCurrency(tag).getBody().getData();

        String gifUrl = gif.getUrl();

        Map<String, String> response = new HashMap<>();

        response.put("url", gifUrl);
        response.put("Actual currency value",latestVal);
        response.put("Latest currency value", historicalVal);
        response.put("Currency", latest.getBase());

       return response;

    }


    private Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private String getStringDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(yesterday());
    }

}
