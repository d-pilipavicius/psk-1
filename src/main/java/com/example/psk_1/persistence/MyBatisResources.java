package com.example.psk_1.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@ApplicationScoped
public class MyBatisResources {

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    private SqlSessionFactory produceSqlSessionFactory() {
        try {
            return new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("mybatisConfig.xml")
            );
        } catch (IOException e) {
            throw new RuntimeException("MyBatisResources.produceSqlSessionFactory(): ", e);
        }
    }
}
