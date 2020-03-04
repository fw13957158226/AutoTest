package com.course.model;

import lombok.Data;

/**
 * Created by fengwei on 2020/3/1.
 */
@Data
public class User {
    private String userName;
    private String password;
    private int id;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;

}
