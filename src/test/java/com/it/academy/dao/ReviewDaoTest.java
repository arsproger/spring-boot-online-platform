package com.it.academy.dao;

import com.it.academy.dao.rowMapper.ReviewRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Course;
import com.it.academy.entities.Review;
import com.it.academy.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class ReviewDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private ReviewDao reviewDao;

    @Test
    void testGetCourseReviewsByAuthorId() {
        User author = User.builder().id(1L).build();
        Course course = Course.builder().id(1L).author(author).build();
        List<Review> expectedReviews = Arrays.asList(
                Review.builder().id(1L).title("Review 1").course(course).build(),
                Review.builder().id(2L).title("Review 2").course(course).build());

        doNothing().when(daoValidate).checkUserExistsById(author.getId());
        when(jdbcTemplate.query(anyString(), any(ReviewRowMapper.class), anyLong()))
                .thenReturn(expectedReviews);

        List<Review> actualReviews = reviewDao.getCourseReviewsByAuthorId(author.getId());

        assertThat(actualReviews).isEqualTo(expectedReviews);
        verify(jdbcTemplate, times(1)).query(anyString(), any(ReviewRowMapper.class), anyLong());
    }

    @Test
    public void testGetReviewsByCourseId() {
        User author = User.builder().id(1L).build();
        Course course = Course.builder().id(1L).author(author).build();
        List<Review> expectedReviews = Arrays.asList(
                Review.builder().id(1L).title("Review 1").course(course).build(),
                Review.builder().id(2L).title("Review 2").course(course).build());

        doNothing().when(daoValidate).checkCourseExistsById(course.getId());
        when(jdbcTemplate.query(anyString(), any(ReviewRowMapper.class), anyLong()))
                .thenReturn(expectedReviews);

        List<Review> actualReviews = reviewDao.getReviewsByCourseId(course.getId());

        assertThat(actualReviews).isEqualTo(expectedReviews);

        verify(jdbcTemplate, times(1)).query(anyString(), any(ReviewRowMapper.class), anyLong());
    }

    @Test
    public void testGetCourseAvgGrade() {
        Long courseId = 1L;
        Double expectedAvgGrade = 4.5;

        doNothing().when(daoValidate).checkCourseExistsById(courseId);
        when(jdbcTemplate.queryForObject(anyString(), eq(Double.class), eq(courseId))).thenReturn(expectedAvgGrade);

        Double actualAvgGrade = reviewDao.getCourseAvgGrade(courseId);

        assertEquals(expectedAvgGrade, actualAvgGrade);
        verify(jdbcTemplate).queryForObject(anyString(), eq(Double.class), eq(courseId));
    }

}
