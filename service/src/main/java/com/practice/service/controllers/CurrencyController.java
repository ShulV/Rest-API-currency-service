package com.practice.service.controllers;

import com.practice.service.model.CurrencyMenuItem;
import com.practice.service.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    // Получение списка названия валют для раскрывающегося меню
    @GetMapping(value = "/all-currency-designations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyMenuItem> getAllCurrencyDesignations() {
        return currencyService.getAllCurrencyDesignations();
    }

    @GetMapping(value = "/initDB", produces = MediaType.APPLICATION_JSON_VALUE)
    public String init() throws IOException {
        currencyService.initDB();
        return "initDB";
    }
}
