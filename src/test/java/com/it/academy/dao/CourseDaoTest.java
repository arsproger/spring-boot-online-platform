package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CourseRowMapper;
import com.it.academy.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class), anyLong()))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getByAuthorId(user.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class), anyLong());
    }

    @Test
    public void testFilterByPriceAsk() {
        Category category = Category.builder().id(1L).title("IT").build();
        Course course1 = Course.builder().category(category).price(new BigDecimal(700)).build();
        Course course2 = Course.builder().category(category).price(new BigDecimal(800)).build();
        Course course3 = Course.builder().category(category).price(new BigDecimal(500)).build();
        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);
        expectedCourses = expectedCourses.stream()
                .sorted(Comparator.comparing(Course::getPrice)).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.filterByPriceAsk(category.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class));
    }

    @Test
    public void testFilterByPriceDesc() {
        Category category = Category.builder().id(1L).title("IT").build();
        Course course1 = Course.builder().category(category).price(new BigDecimal(700)).build();
        Course course2 = Course.builder().category(category).price(new BigDecimal(800)).build();
        Course course3 = Course.builder().category(category).price(new BigDecimal(500)).build();
        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);
        expectedCourses = expectedCourses.stream()
                .sorted(Comparator.comparing(Course::getPrice).reversed()).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.filterByPriceDesc(category.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class));
    }

    @Test
    public void testGetByLanguage() {
        Category category = Category.builder().id(1L).title("IT").build();
        Course course1 = Course.builder().id(1L).category(category).language("ru").build();
        Course course2 = Course.builder().id(2L).category(category).language("ru").build();
        List<Course> expectedCourses = Arrays.asList(course1, course2);

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class), anyString()))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getByLanguage("ru", category.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class), anyString());
    }

    @Test
    public void testGetCoursesByUserCart() {
        Course course1 = Course.builder().id(1L).name("Java 8").build();
        Course course2 = Course.builder().id(2L).name("SQL").build();
        Course course3 = Course.builder().id(3L).name("Spring framework").build();
        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);
        Cart cart = Cart.builder().id(1L).courses(expectedCourses).build();
        User user = User.builder().id(1L).fullName("Arsen Bekboev").cart(cart).build();

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class), anyLong()))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getCoursesByUserCart(user.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class), anyLong());
    }

    @Test
    public void testGetCourseByCategoryId() {
        Category category = Category.builder().id(1L).title("IT").build();
        Course course1 = Course.builder().name("Java 8").category(category).build();
        Course course2 = Course.builder().name("SQL").category(category).build();
        Course course3 = Course.builder().name("Spring framework").category(category).build();

        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class), anyLong()))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getCourseByCategoryId(category.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class), anyLong());
    }

    @Test
    public void testGetCourseByName() {
        Course course1 = Course.builder().id(1L).name("Java").build();
        Course course2 = Course.builder().id(2L).name("Java").build();
        Course course3 = Course.builder().id(3L).name("Java").build();

        List<Course> expectedCourses = Arrays.asList(course1, course2, course3);

        when(jdbcTemplate.query(anyString(), any(CourseRowMapper.class), anyString()))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getCourseByName("Java");

        assertThat(actualCourses).isEqualTo(expectedCourses);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CourseRowMapper.class), anyString());
    }

    @Test
    public void testGetCourseDurationSum() {
        Lesson lesson1 = Lesson.builder().duration(5.0).build();
        Lesson lesson2 = Lesson.builder().duration(3.0).build();
        Lesson lesson3 = Lesson.builder().duration(9.0).build();

        Section section1 = Section.builder().lessons(Arrays.asList(lesson1, lesson2)).build();
        Section section2 = Section.builder().lessons(Collections.singletonList(lesson3)).build();

        Course course = Course.builder().id(1L).sections(Arrays.asList(section1, section2)).build();

        Double expectedDurationSum = course.getSections()
                .stream().flatMapToDouble(element -> element.getLessons()
                        .stream().mapToDouble(Lesson::getDuration)).average().orElse(0);

        when(jdbcTemplate.queryForObject(anyString(), eq(Double.class), anyLong()))
                .thenReturn(expectedDurationSum);

        Double actualDurationSum = courseDao.getCourseDurationSum(course.getId());

        assertThat(actualDurationSum).isEqualTo(expectedDurationSum);

        verify(jdbcTemplate, times(1))
                .queryForObject(anyString(), eq(Double.class), anyLong());
    }


    @Test
    public void testSetImageUrl() {
        Course course = Course.builder().id(1L).name("Java").build();
        String imageUrl = "java.mp4";

        when(jdbcTemplate.update(anyString(), anyString(), anyLong()))
                .thenReturn(0);

        courseDao.setImageUrl(imageUrl, course.getId());

        verify(jdbcTemplate, times(1))
                .update(anyString(), anyString(), anyLong());
    }

}
