package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CategoryRowMapper;
import com.it.academy.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Category> getCategoriesByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM categories WHERE title ILIKE ?",
                new CategoryRowMapper(), ("%" + title + "%"));
    }

}
