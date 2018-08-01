package com.prospring.jdbc.repository;

import com.prospring.jdbc.entities.Singer;
import com.prospring.jdbc.repository.operation.InsertSinger;
import com.prospring.jdbc.repository.operation.SelectAllSingers;
import com.prospring.jdbc.repository.operation.SelectSingersByFirstName;
import com.prospring.jdbc.repository.operation.UpdateSingerById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 12:48
 **/

@Repository("advJdbcSingerRepository")
public class AdvancedJdbcSingerRepository implements SingerRepository{
    private static final Logger logger = LoggerFactory.getLogger(AdvancedJdbcSingerRepository.class);
    private DataSource dataSource;
    private MappingSqlQuery<Singer> selectAllSingers;
    private MappingSqlQuery<Singer> selectSingersByFirstName;
    private SqlUpdate updateSingerById;
    private SqlUpdate insertSinger;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingersByFirstName = new SelectSingersByFirstName(dataSource);
        this.updateSingerById = new UpdateSingerById(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List findByFirstName(String firstName) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", firstName);
        return selectSingersByFirstName.executeByNamedParam(params);
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> params = new HashMap<>();
        params.put("lastName", singer.getLastName());
        params.put("firstName", singer.getFirstName());
        params.put("birthDate", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(params, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("Generated key: " + singer.getId());
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> params = new HashMap<>();
        params.put("singerId", singer.getId());
        params.put("lastName", singer.getLastName());
        params.put("firstName", singer.getFirstName());
        params.put("birthDate", singer.getBirthDate());
        updateSingerById.updateByNamedParam(params);
    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public List<Singer> findAllWithDetail() {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }
}
