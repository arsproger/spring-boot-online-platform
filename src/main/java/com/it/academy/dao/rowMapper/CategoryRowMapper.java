package com.it.academy.dao.rowMapper;

import com.it.academy.entities.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setTitle(resultSet.getString("title"));
        return category;
    }
}
