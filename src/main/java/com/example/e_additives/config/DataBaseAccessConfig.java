package com.example.e_additives.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataBaseAccessConfig {

    @Value("${datasource.url}")
    private String URL;

    @Value("${datasource.username}")
    private String USERNAME;

    @Value("${datasource.password}")
    private String PASSWORD;

    @Value("${datasource.driver}")
    private String DRIVER;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setDriverClassName(DRIVER);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return  new JdbcTemplate(getDataSource());
    }

}
