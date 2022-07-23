package com.practice.service.dao;

import com.practice.service.dao.mappers.CurrencyIdCharcodeMapper;
import com.practice.service.dao.mappers.CurrencyMapper;
import com.practice.service.dao.mappers.CurrencyMenuMapper;
import com.practice.service.model.Currency;
import com.practice.service.model.CurrencyMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        jdbcTemplate.batchUpdate("INSERT INTO \"Currency\" (\"PK_id\", \"numcode\", \"charcode\", \"name\") " +
                        "VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING",
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
    public String getIdByCharcode(String charcode) {
        return jdbcTemplate.query("SELECT * FROM \"Currency\" WHERE \"charcode\" =?",
                new Object[]{charcode}, new CurrencyIdCharcodeMapper()).get(0);
    }

}
