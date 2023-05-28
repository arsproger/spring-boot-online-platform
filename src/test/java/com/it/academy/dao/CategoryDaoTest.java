package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CategoryRowMapper;
import com.it.academy.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CategoryDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CategoryDao categoryDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoriesByTitle() {
        String title = "Category";

        List<Category> expectedCategories = Arrays.asList(
                Category.builder().id(1L).title("Category 1").build(),
                Category.builder().id(2L).title("Category 2").build());

        when(jdbcTemplate.query(anyString(), any(CategoryRowMapper.class), any()))
                .thenReturn(expectedCategories);

        List<Category> actualCategories = categoryDao.getCategoriesByTitle(title);

        assertEquals(expectedCategories, actualCategories);
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CategoryRowMapper.class), any());
    }
}
