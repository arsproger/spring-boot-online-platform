package com.it.academy.dao;

import com.it.academy.dao.rowMapper.ReviewRowMapper;
import com.it.academy.entities.Course;
import com.it.academy.entities.Review;
import com.it.academy.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReviewDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ReviewDao reviewDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCourseReviewsByAuthorId() {
        User author = User.builder().id(1L).build();
        Course course = Course.builder().id(1L).author(author).build();
        List<Review> expectedReviews = Arrays.asList(
                Review.builder().id(1L).title("Review 1").course(course).build(),
                Review.builder().id(2L).title("Review 2").course(course).build());

        when(jdbcTemplate.query(anyString(), any(ReviewRowMapper.class), anyLong()))
                .thenReturn(expectedReviews);

        List<Review> actualReviews = reviewDao.getCourseReviewsByAuthorId(author.getId());

        assertThat(actualReviews).isEqualTo(expectedReviews);
        verify(jdbcTemplate, times(1)).query(anyString(), any(ReviewRowMapper.class), anyLong());
    }

    @Test
    void getReviewsByCourseId() {
    }

    @Test
    void getCourseAvgGrade() {
    }
}