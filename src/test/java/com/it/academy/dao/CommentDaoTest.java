package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CommentRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Comment;
import com.it.academy.entities.Lesson;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class CommentDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private CommentDao commentDao;

    @Test
    public void testGetCommentsByLessonId() {
        Lesson lesson = Lesson.builder().id(1L).build();
        List<Comment> expectedComments = Arrays.asList(
                Comment.builder().id(1L).title("Comment 1").lesson(lesson).build(),
                Comment.builder().id(2L).title("Comment 2").lesson(lesson).build());

        doNothing().when(daoValidate).checkLessonExistsById(lesson.getId());
        when(jdbcTemplate.query(anyString(), any(CommentRowMapper.class), eq(lesson.getId())))
                .thenReturn(expectedComments);

        List<Comment> actualComments = commentDao.getCommentsByLessonId(lesson.getId());

        assertThat(actualComments).isEqualTo(expectedComments);
        verify(jdbcTemplate, times(1)).query(anyString(), any(CommentRowMapper.class), eq(lesson.getId()));
    }

}