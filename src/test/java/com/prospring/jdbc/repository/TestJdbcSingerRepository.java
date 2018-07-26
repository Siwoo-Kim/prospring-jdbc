package com.prospring.jdbc.repository;

import com.prospring.jdbc.entities.Singer;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-25 오전 11:58
 **/


public class TestJdbcSingerRepository {

    JdbcSingerRepository jdbcSingerRepository;


    @Test
    public void fetch() {
        jdbcSingerRepository = new JdbcSingerRepository();
        List<Singer> singers = jdbcSingerRepository.findAll();
        System.out.println(singers);
        assertNotNull(singers);
        int prevSize = singers.size();

        Singer singer = new Singer();
        singer.setFirstName("Test");
        singer.setLastName("Singer");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010,10,10);
        singer.setBirthDate(calendar.getTime());
        jdbcSingerRepository.insert(singer);
        assertEquals(jdbcSingerRepository.findAll().size(), prevSize+1);

        jdbcSingerRepository.delete(singer.getId());
        assertEquals(jdbcSingerRepository.findAll().size(), prevSize);
        System.out.println(jdbcSingerRepository.findAll());
    }

}
