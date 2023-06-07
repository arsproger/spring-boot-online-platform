package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CategoryRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Category;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class CategoryDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private CategoryDao categoryDao;

    @Test
    void testGetCategoriesByTitle() {
        String title = "Category";

        List<Category> expectedCategories = Arrays.asList(
                Category.builder().id(1L).title("Category 1").build(),
                Category.builder().id(2L).title("Category 2").build());

        when(jdbcTemplate.query(anyString(), any(CategoryRowMapper.class), any()))
                .thenReturn(expectedCategories);

        List<Category> actualCategories = categoryDao.getCategoriesByTitle(title);

        assertThat(actualCategories).isEqualTo(expectedCategories);
        verify(jdbcTemplate, times(1)).query(anyString(), any(CategoryRowMapper.class), any());
    }

    @Test
    public void testSetImageUrl() {
        Category category = Category.builder().id(1L).title("IT").build();
        String imageUrl = "java.jpg";

        doNothing().when(daoValidate).checkCategoryExistsById(category.getId());
        when(jdbcTemplate.update(anyString(), anyString(), anyLong()))
                .thenReturn(0);

        categoryDao.setImageUrl(imageUrl, category.getId());

        verify(jdbcTemplate, times(1))
                .update(anyString(), anyString(), anyLong());
    }

}
