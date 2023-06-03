package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CategoryRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Category> getCategoriesByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM categories WHERE title ILIKE ?",
                new CategoryRowMapper(), ("%" + title + "%"));
    }

    public void setImageUrl(String imageUrl, Long categoryId) {
        daoValidate.checkCategoryExistsById(categoryId);
        jdbcTemplate.update("UPDATE categories set image_url = ? where id = ?", imageUrl, categoryId);
    }

}
