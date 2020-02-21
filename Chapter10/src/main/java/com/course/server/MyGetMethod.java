package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by fengwei on 2020/2/19.
 */
@RestController
@Api(value="/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value="/getCookies",method= RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取到cookie",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }

    @RequestMapping(value="/get/with/cookies",method = RequestMethod.POST)
    @ApiOperation(value="需要携带cookies信息才能访问的post请求",httpMethod = "POST")
    public String getWithCookies(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须携带cookies信息来";
        }

        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "这是一个携带了cookie的信息";
            }
        }
        return "你必须携带cookies信息来";
    }

    @RequestMapping(value="/get/with/param",method=RequestMethod.GET)
    @ApiOperation(value ="这是携带参数的get方法1",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);

        return myList;

    }

    @RequestMapping(value="/get/with/param/{start}/{end}",method=RequestMethod.GET)
    @ApiOperation(value="这是第二个携带参数的方法get",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",2);
        myList.put("衬衫",300);

        return myList;

    }
}
