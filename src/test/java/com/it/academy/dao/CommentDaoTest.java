package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CommentRowMapper;
import com.it.academy.entities.Comment;
import com.it.academy.entities.Lesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CommentDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private CommentDao commentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCommentsByLessonId() {
        Lesson lesson = Lesson.builder().id(1L).build();
        List<Comment> expectedComments = Arrays.asList(
                Comment.builder().id(1L).title("Comment 1").lesson(lesson).build(),
                Comment.builder().id(2L).title("Comment 2").lesson(lesson).build());

        when(jdbcTemplate.query(anyString(), any(CommentRowMapper.class), eq(lesson.getId())))
                .thenReturn(expectedComments);

        List<Comment> actualComments = commentDao.getCommentsByLessonId(lesson.getId());

        assertThat(actualComments).isEqualTo(expectedComments);
    }
}