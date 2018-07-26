package com.prospring.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.net.URL;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-25 오후 12:48
 **/

@Configuration
@PropertySource({"classpath:/properties/jdbc.properties"})
public class DatabaseConfig {

    @Value("${jdbc.driverClassName}")
    private Class driverClass;
    @Value("${jdbc.url}")
    private String databaseUrl;
    @Value("${jdbc.username}")
    private String databaseUsername;
    @Value("${jdbc.password}")
    private String databasePassword;


    @Lazy //Init the bean only the bean is requested
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }
}
