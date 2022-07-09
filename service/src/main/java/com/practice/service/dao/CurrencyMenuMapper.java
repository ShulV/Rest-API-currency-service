package com.practice.service.dao;

import com.practice.service.model.CurrencyMenuItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMenuMapper implements RowMapper<CurrencyMenuItem> {
    @Override
    public CurrencyMenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CurrencyMenuItem(rs.getString("charcode"), rs.getString("name"));
    }
}
