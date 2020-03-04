package com.course.mapper;

import com.course.model.User;
import org.springframework.context.annotation.Bean;

/**
 * Created by fengwei on 2020/2/21.
 */
public interface UserMapper {
    Integer getUserCount();
    Integer addUser(User user);
    Integer updateUser(User user);
    Integer delUser(int id);
}
