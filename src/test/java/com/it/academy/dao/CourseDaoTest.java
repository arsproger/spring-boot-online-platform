package com.it.academy.dao;

import com.it.academy.models.Cart;
import com.it.academy.models.Course;
import com.it.academy.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CourseDao courseDao;

    @Test
    public void testGetByAuthorId() {
        User user = User.builder().id(1L).build();
        Course course1 = Course.builder().id(1L).author(user).build();
        Course course2 = Course.builder().id(2L).author(user).build();
        List<Course> expectedCourses = Arrays.asList(course1, course2);

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class), any(Long.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getByAuthorId(user.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);
    }

    @Test
    public void testFilterByPriceAsk() {
        Course course1 = Course.builder().price(new BigDecimal(700)).build();
        Course course2 = Course.builder().price(new BigDecimal(800)).build();
        Course course3 = Course.builder().price(new BigDecimal(500)).build();
        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);
        expectedCourses = expectedCourses.stream()
                .sorted(Comparator.comparing(Course::getPrice)).collect(Collectors.toList());

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.filterByPriceAsk();

        assertThat(actualCourses).isEqualTo(expectedCourses);
    }

    @Test
    public void testFilterByPriceDesc() {
        Course course1 = Course.builder().price(new BigDecimal(700)).build();
        Course course2 = Course.builder().price(new BigDecimal(800)).build();
        Course course3 = Course.builder().price(new BigDecimal(500)).build();
        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);
        expectedCourses = expectedCourses.stream()
                .sorted(Comparator.comparing(Course::getPrice).reversed()).collect(Collectors.toList());

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.filterByPriceDesc();

        assertThat(actualCourses).isEqualTo(expectedCourses);
    }

    @Test
    public void testGetByLanguage() {
        Course course1 = Course.builder().id(1L).language("ru").build();
        Course course2 = Course.builder().id(2L).language("ru").build();
        List<Course> expectedCourses = Arrays.asList(course1, course2);

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class), any(String.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getByLanguage("ru");

        assertThat(actualCourses).isEqualTo(expectedCourses);
    }

    @Test
    public void testGetCoursesByUserCart() {
        Course course1 = Course.builder().id(1L).name("Java 8").build();
        Course course2 = Course.builder().id(2L).name("SQL").build();
        Course course3 = Course.builder().id(3L).name("Spring framework").build();
        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);
        Cart cart = Cart.builder().id(1L).courses(expectedCourses).build();
        User user = User.builder().id(1L).fullName("Arsen Bekboev").cart(cart).build();

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class), any(Long.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getCoursesByUserCart(user.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);
    }


}
