package com.course.model;

import lombok.Data;

/**
 * Created by fengwei on 2020/3/1.
 */
@Data
public class AddUserCase {
    private String userName;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;
}
