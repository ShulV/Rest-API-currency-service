package com.practice.service.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyMapper implements RowMapper<List<String>> {
    @Override
    public List<String> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ArrayList<String> namesOfCurrencies = new ArrayList<>();
        while (resultSet.next()) {
            namesOfCurrencies.add(resultSet.getString("name"));
        }

        return namesOfCurrencies;
    }
}