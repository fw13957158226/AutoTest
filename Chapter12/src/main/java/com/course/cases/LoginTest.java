package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.zip.Inflater;

/**
 * Created by fengwei on 2020/3/1.
 */
public class LoginTest {
    @BeforeTest(groups = "loginTrue" ,description = "测试准备工作")
    public void beforeTest(){
        TestConfig.getUserInfoUrl= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl= ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl=ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }

    @Test(groups = "loginTrue",description = "用户登陆成功接口测试")
    public void loginTrue() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        String result=getResult(loginCase);
        Thread.sleep(3000);
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.loginUrl);
        JSONObject param=new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        post.setHeader("content-type","application/json");

        StringEntity entity=new StringEntity(param.toString());
        post.setEntity(entity);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
        return result;

    }

    @Test(groups = "loginFalse",description = "用户登陆接口失败测试")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase=session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

    }
}
