package com.course.model;

import lombok.Data;

/**
 * Created by fengwei on 2020/3/1.
 */
@Data
public class LoginCase {
    private int id;
    private String userName;
    private String password;
    private String expected;
}
