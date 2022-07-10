package com.practice.service.dao;

import com.practice.service.model.Currency;
import com.practice.service.model.CurrencyMenuItem;
import com.practice.service.model.DayCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Currency> getAll() {
        return jdbcTemplate.query("SELECT * FROM \"Currency\"", new CurrencyMapper());
    }
    // Получение списка названия валют для раскрывающегося меню
    public List<CurrencyMenuItem> getAllCurrencyDesignations() {
        return jdbcTemplate.query("SELECT charcode, name FROM \"Currency\"", new CurrencyMenuMapper());
    }

    public void batchCurrencyUpdate(List<Currency> currencyList) {
        jdbcTemplate.batchUpdate("INSERT INTO \"Currency\"(\"PK_id\", numcode, charcode, name)" +
                        "VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, currencyList.get(i).getPK_Id());
                        ps.setInt(2, currencyList.get(i).getNumCode());
                        ps.setString(3, currencyList.get(i).getCharCode());
                        ps.setString(4, currencyList.get(i).getName());
                    }

                    @Override
                    public int getBatchSize() {
                        return currencyList.size();
                    }
                });
    }

//    public List<Currency> index() {
//        return jdbcTemplate.query("SELECT * FROM Currency",
//                new BeanPropertyRowMapper<>(Currency.class));
//    }
//
//    public Currency show(int PK_id) {
//        return jdbcTemplate.query("SELECT * FROM Currency WHERE PK_id=?",
//                        new Object[]{PK_id}, new BeanPropertyRowMapper<>(Currency.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Currency currency) {
//        jdbcTemplate.update("INSERT INTO Currency VALUES(?, ?, ?, ?)", currency.getPK_Id(),
//                currency.getNumCode(), currency.getCharCode(), currency.getName());
//    }
//
//    public void update(int PK_id, Currency updatedCurrency) {
//        jdbcTemplate.update("UPDATE Currency SET numcode=?, charcode=?, name=? WHERE PK_id=?",
//                updatedCurrency.getNumCode(), updatedCurrency.getCharCode(), updatedCurrency.getName(), PK_id);
//    }
//
//    public void delete(int PK_id) {
//        jdbcTemplate.update("DELETE FROM Currency WHERE PK_id=?", PK_id);
//    }
}
