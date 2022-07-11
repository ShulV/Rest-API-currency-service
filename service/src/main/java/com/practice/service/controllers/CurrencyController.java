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

    @GetMapping(value = "/testDB", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testDB() throws IOException {
//        dayCurrencyService.insert(new DayCurrency(444, 77.77, new Date(1999, 12, 12), 100, "pk33"), "Евро");
        currencyService.initDB();
        return "testDB";
    }

//    @GetMapping()
//    public String index() {
//        return "people/index";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id) {
//        return "currencies/show";
//    }
//
//    @PostMapping()
//    public String create(Currency currency) {
//        currencyDAO.save(currency);
//        return "redirect:/currencies";
//    }
}
