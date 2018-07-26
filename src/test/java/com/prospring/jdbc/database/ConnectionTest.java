package com.prospring.jdbc.database;

import com.prospring.jdbc.config.DatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertNotNull;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-25 오후 12:43
 **/

public class ConnectionTest {

    ApplicationContext c;

    @Test
    public void getConnection() throws SQLException {
//        c = new ClassPathXmlApplicationContext("spring/jdbc-context.xml");
//        try(Connection connection = c.getBean(DataSource.class).getConnection()) {
//            assertNotNull(connection);
//        }
        c = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        try(Connection connection = c.getBean(DataSource.class).getConnection()) {
            assertNotNull(connection);
        }
    }

    @Test
    public void embeddedDBConnection() {

    }
}
































