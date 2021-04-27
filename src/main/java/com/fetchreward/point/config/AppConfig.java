package com.fetchreward.point.config;

import java.util.Properties;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
public class AppConfig {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/point");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.fetchreward.point.domain");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(jpaProperties());

        return entityManagerFactory;
    }

    public Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // hbm = hibernate mapping, ddl = data definition
        jpaProperties.setProperty("spring.session.store-type", "jdbc");
        // language

        return jpaProperties;
    }

}
