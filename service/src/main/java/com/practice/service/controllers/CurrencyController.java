package com.practice.service.controllers;

import com.practice.service.CurrencyService;
import com.practice.service.dao.CurrencyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/all-currency-names", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllCurrencyNames() {
        return currencyService.getAllCurrencyNames();
    }
    //

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
