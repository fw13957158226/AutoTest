package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by fengwei on 2020/2/19.
 */
public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");

    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;

        HttpGet get =new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        this.store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name=" +name + "cookie value =" +value);
        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri=bundle.getString("test.post.with.cookies");
        String testUrl=this.url+uri;

        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client=new DefaultHttpClient();

        //声明一个方法，这个方法是post方法
        HttpPost post =new HttpPost(testUrl);
        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        // 设置请求头信息,header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        // 声明一个对象来进行响应结果存储
        String result;
        // 设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response=client.execute(post);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson=new JSONObject(result);
        String sucess=(String) resultJson.get("huhansan");
        String status=(String )resultJson.get("status");
        Assert.assertEquals("success",sucess);
        Assert.assertEquals("1",status);
    }
}
