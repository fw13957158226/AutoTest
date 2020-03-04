package com.course.model;

import lombok.Data;

/**
 * Created by fengwei on 2020/3/1.
 */
@Data
public class UpdateUserInfoCase {
    private int id;
    private int userId;
    private String userName;
    private String sex;
    private String age;
    private String permission;
    private String expected;
    private String isDelete;
}
