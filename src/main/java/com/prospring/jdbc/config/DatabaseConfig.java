package com.prospring.jdbc.config;

import com.prospring.jdbc.repository.SingerRepository;
import com.prospring.jdbc.repository.SpringJdbcSingerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

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

    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:/sql/h2-schema.sql","classpath:/sql/h2-test-data.sql")
                .build();
    }

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    SingerRepository singerRepository() {
        SpringJdbcSingerRepository repository = new SpringJdbcSingerRepository();
        repository.setJdbcTemplate(namedParameterJdbcTemplate());
        return repository;
    }
//    @Lazy //Init the bean only the bean is requested
//    @Bean
//    public DataSource dataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClass(driverClass);
//        dataSource.setUrl(databaseUrl);
//        dataSource.setUsername(databaseUsername);
//        dataSource.setPassword(databasePassword);
//        return dataSource;
//    }
}
