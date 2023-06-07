package com.it.academy.dao;

import com.it.academy.dao.rowMapper.SubscriptionRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Subscription;
import com.it.academy.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class SubscriptionDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private SubscriptionDao subscriptionDao;

    @Test
    public void testGetSubscriptionsByUserId() {
        User user = User.builder().id(1L).build();
        Subscription subscription1 = Subscription.builder().id(1L).user(user).build();
        Subscription subscription2 = Subscription.builder().id(2L).user(user).build();
        List<Subscription> expectedSubscriptions = Arrays.asList(subscription1, subscription2);

        doNothing().when(daoValidate).checkUserExistsById(user.getId());
        when(jdbcTemplate.query(anyString(), any(SubscriptionRowMapper.class), eq(user.getId())))
                .thenReturn(expectedSubscriptions);

        List<Subscription> actualSubscriptions = subscriptionDao.getSubscriptionsByUserId(user.getId());

        assertEquals(expectedSubscriptions, actualSubscriptions);
        verify(jdbcTemplate).query(anyString(), any(SubscriptionRowMapper.class), eq(user.getId()));
    }

}