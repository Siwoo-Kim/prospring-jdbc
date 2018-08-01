package com.prospring.jdbc.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 1:17
 **/


public class TestAppConfig {

    ApplicationContext c;

    @Before
    public void setup() {
        c = new AnnotationConfigApplicationContext(AppConfig.class);
        assertNotNull(c);
    }

    @Test
    public void test() {

    }
}
