package com.prospring.jdbc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 12:51
 **/

@Configuration
@PropertySource(value = {"classpath:/properties/jdbc.properties"})
@ComponentScan(basePackages = "com.prospring.jdbc")
public class AppConfig {
    @Value("${jdbc.url}")
    private String databaseUrl;
    @Value("${jdbc.driverClassName}")
    private String databaseDriverClassName;
    @Value("${jdbc.username}")
    private String databaseUsername;
    @Value("${jdbc.password}")
    private String databasePassword;


    @Bean
    DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setDriverClassName(databaseDriverClassName);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

}
