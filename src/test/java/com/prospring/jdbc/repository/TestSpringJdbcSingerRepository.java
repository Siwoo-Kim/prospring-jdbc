package com.prospring.jdbc.repository;

import com.prospring.jdbc.config.DatabaseConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-26 오전 10:03
 **/


public class TestSpringJdbcSingerRepository {

    ApplicationContext c;
    SingerRepository singerRepository;


    @Before
    public void resolvingBean() {
        c = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        singerRepository = c.getBean(SpringJdbcSingerRepository.class);
    }

    @Test
    public void testFindNameById() {
        System.out.println(singerRepository.findNameById(1l));
        assertEquals("Siwoo Kim", singerRepository.findNameById(1l));
    }

    @Test
    public void testFindAll() {
        assertTrue(singerRepository.findAll().size() > 0);
        assertTrue(singerRepository.findByFirstName("Siwoo").size() > 0);
        System.out.println(singerRepository.findAll());
        System.out.println(singerRepository.findByFirstName("Siwoo"));
    }

    @Test
    public void testFindAllWithAlbums() {
        System.out.println(singerRepository.findAllWithDetail());
        assertTrue(singerRepository.findAllWithDetail().size() > 0);
    }

}
