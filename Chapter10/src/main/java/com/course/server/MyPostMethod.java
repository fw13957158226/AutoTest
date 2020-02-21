package com.course.server;

import com.course.bean.User;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengwei on 2020/2/20.
 */
@RestController
@Api(value="/",description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {

    private static Cookie cookie;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆成功，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "password",required = true) String password){
        if(userName.equals("zhangsan") && password.equals("123456")){
            cookie =new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功了";
        }
        return "用户名或者密码错误";

    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value="获取用户列表",httpMethod = "POST")
    public String  getUserList(HttpServletRequest request,
                            @RequestBody User u){
        Cookie[] cookies =request.getCookies();
        User user;
        for (Cookie c:cookies){
            if(c.getName() == "login"
                    && c.getValue() == "true"
                    && u.getUserName() == "zhangsan"
                    && u.getPassword() == "123456"){
                user =new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();

            }
        }
        return "参数不合法";


    }


}
