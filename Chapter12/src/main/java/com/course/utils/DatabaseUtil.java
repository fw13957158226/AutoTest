package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by fengwei on 2020/3/1.
 */
public class DatabaseUtil {
    public static SqlSession getSqlSession() throws IOException {
        Reader reader= Resources.getResourceAsReader("databaseConfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession=factory.openSession();
        return sqlSession;
    }
}
