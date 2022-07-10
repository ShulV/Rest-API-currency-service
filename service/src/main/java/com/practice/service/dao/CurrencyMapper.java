package com.practice.service.dao;

import com.practice.service.model.Currency;
import com.practice.service.model.CurrencyMenuItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements RowMapper<Currency> {
    @Override
    public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
        Currency currency = new Currency();
        currency.setPK_Id(rs.getString("PK_id"));
        currency.setNumCode(rs.getInt("numcode"));
        currency.setCharCode(rs.getString("charcode"));
        currency.setName(rs.getString("name"));
        return currency;
    }
}
