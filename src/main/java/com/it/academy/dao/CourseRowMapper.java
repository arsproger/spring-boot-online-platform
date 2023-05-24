package com.it.academy.dao;

import com.it.academy.dto.CourseDto;
import com.it.academy.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CourseRowMapper implements RowMapper<CourseDto> {

    @Override
    public CourseDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(resultSet.getLong("id"));
        courseDto.setName(resultSet.getString("name"));
        courseDto.setDescription(resultSet.getString("description"));
        courseDto.setPrice(resultSet.getBigDecimal("price"));
        courseDto.setLanguage(resultSet.getString("language"));
        courseDto.setCreated(resultSet.getDate("created").toLocalDate());
        return courseDto;
    }
}
