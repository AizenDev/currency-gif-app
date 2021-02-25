package com.example.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ConverterController {
    @Autowired
    public ConverterService converterService;

    @RequestMapping(value = "/convert",method = RequestMethod.GET)
    public Map<String, String> getCurrency(@RequestParam String base) {
        return converterService.convertCurrency(base);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(){
        return "Hello";
    }
}
