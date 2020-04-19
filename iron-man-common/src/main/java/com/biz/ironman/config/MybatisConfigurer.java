package com.biz.ironman.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 李浩东
 * @description tk Mybatis 配置类
 * @date 2020/4/18 17:04
 **/

public class MybatisConfigurer {

    private DataSource dataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //添加XML目录  注意: resource目录下必须要有mapper文件夹  否则报错,此处也是坑
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("mapper/*.xml"));
        return factoryBean.getObject();
    }


    @Configuration
    @AutoConfigureAfter(MybatisConfigurer.class)
    public static class  MyBatisMapperScannerConfigurer {

        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
            mapperScannerConfigurer.setBasePackage("com.biz.ironman.dao.*");
            Properties properties = new Properties();
            properties.setProperty("mappers", "com.bix.common.mapper.CommonMapper");
            properties.setProperty("notEmpty", "false");
            properties.setProperty("IDENTITY", "MYSQL");
            mapperScannerConfigurer.setProperties(properties);
            return mapperScannerConfigurer;
        }
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
    Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("helperDialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
}
}
