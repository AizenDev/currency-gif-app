package com.example.converter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "GifClient", url = "${feign.client.gifs-client-url}?api_key=${key.GIPHY_API_KEY}&limit=1")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<GifBaseDTO> getGifByCurrency(@RequestParam String tag);
}
