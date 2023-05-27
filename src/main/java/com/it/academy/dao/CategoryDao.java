package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CategoryRowMapper;
import com.it.academy.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Category> getCategoriesByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM categories WHERE title ILIKE ?",
                new CategoryRowMapper(), ("%" + title + "%"));
    }

}
