package com.course.bean;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

/**
 * Created by fengwei on 2020/2/20.
 */
@Data
public class User {
    private String userName;
    private String password;
    private String name;
    private String sex;
    private String age;
}
