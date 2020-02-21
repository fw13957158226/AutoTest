package com.course.controller;

import com.course.mapper.UserMapper;
import com.course.model.User;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengwei on 2020/2/20.
 */
@RestController
@Api(value="v1",description = "这是我的第一个版本的demo")
@RequestMapping("v1")
public class demo {
    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value="/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value="可以获取到用户数",httpMethod = "GET")
    public int getUserCount(){
//        int t= template.selectOne("getUserCount");
        int t = userMapper.getUserCount();
        System.out.println("t=" + t);
        return t;
    }

    @RequestMapping(value = "/addUser",method=RequestMethod.POST)
    public int addUser(@RequestBody User user){
        int result=userMapper.addUser(user);
        return result;
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        return userMapper.updateUser(user);
    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public int delUser(@RequestParam int id){
        return userMapper.delUser(id);
    }
}
