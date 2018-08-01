package com.prospring.jdbc.repository;

import com.prospring.jdbc.config.AppConfig;
import com.prospring.jdbc.entities.Singer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 1:28
 **/


public class TestAdvancedJdbcSingerRepository {

    ApplicationContext c;
    AdvancedJdbcSingerRepository repository;

    @Before
    public void setup() {
        c = new AnnotationConfigApplicationContext(AppConfig.class);
        repository = c.getBean(AdvancedJdbcSingerRepository.class);
    }

    @Test
    public void selectAll() {
        repository.findAll().stream()
                .forEach(System.out::println);
        assertNotNull(repository.findAll());
    }

    @Test
    public void selectByFirstName() throws Throwable {
        repository.findByFirstName("siwoo")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        repository.findByFirstName("siwoo")
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());

    }

    @Test
    public void updateById() {
        Singer singer = (Singer) repository.findByFirstName("siwoo")
                .stream()
                .findFirst()
                .orElse(null);

        String lastName = singer.getLastName();
        singer.setLastName( "Kim".equals(lastName)? "Lee": "Kim");
        repository.update(singer);

        singer = (Singer) repository.findByFirstName("siwoo")
                .stream()
                .findFirst()
                .orElse(null);

        assertFalse(singer.getLastName().equals(lastName));
        System.out.println(singer);
    }

    private static final Calendar calendar = Calendar.getInstance();
    private static final Date birthDate;
    static {
        calendar.set(1989,04,05);
        birthDate = calendar.getTime();
    }
    @Test
    public void insert() {
        Singer singer = new Singer();
        singer.setLastName("Dummy" + Math.random());
        singer.setFirstName("Kim");
        singer.setBirthDate(birthDate);
        repository.insert(singer);

        Long singerId = singer.getId();
        assertNotNull(singer);
        repository.delete(singerId);
    }
}



































