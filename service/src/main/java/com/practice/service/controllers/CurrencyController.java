package com.practice.service.controllers;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyDAO currencyDAO;

    @Autowired
    public CurrencyController(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @GetMapping("/all-currency-names")
    public List<String> getAllCurrencyNames() {
        return currencyDAO.getAllCurrencyNames();
    }

    @GetMapping()
    public String index() {
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id) {
        return "currencies/show";
    }

    @PostMapping()
    public String create(Currency currency) {
        currencyDAO.save(currency);
        return "redirect:/currencies";
    }
}
