package com.prospring.jdbc.repository;

import com.prospring.jdbc.entities.Album;
import com.prospring.jdbc.entities.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.*;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-26 오전 9:59
 **/


public class SpringJdbcSingerRepository implements SingerRepository{
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final RowMapper<Singer> singerRowMapper = (resultSet, rowNum) -> {
        Singer singer = new Singer();
        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));
        return singer;
    };
    public static final String SQL_FIND_ALL_JOINING_ALBUMS =
            "select s.id, s.first_name, s.last_name, s.birth_date, " +
                    "a.id as album_id, a.title, a.release_date " +
                    "from singer s left join album a " +
                    "on s.id = a.singer_id ";
    private static final ResultSetExtractor<List<Singer>> singerJoiningAlbumExtractor = resultSet -> {
      Map<Long, Singer> map = new HashMap<>();
        while (resultSet.next()) {
          Long id = resultSet.getLong("id");
          Singer singer = map.get(id);
          if(singer == null) {
              singer = new Singer();
              singer.setId(id);
              singer.setFirstName(resultSet.getString("first_name"));
              singer.setLastName(resultSet.getString("last_name"));
              singer.setBirthDate(resultSet.getDate("birth_date"));
              singer.setAlbums(new ArrayList<>());
              map.put(id, singer);
          }
          Long albumId = resultSet.getLong("album_id");
          if(albumId > 0) {
              Album album = new Album();
              album.setSingerId(id);
              album.setId(albumId);
              album.setReleaseDate(resultSet.getDate("release_date"));
              album.setTitle(resultSet.getString("title"));
              singer.addAlbum(album);
          }
      }
      return new ArrayList<>(map.values());
    };


    private static final String SQL_FIND_NAME_BY_ID
            = "select first_name || ' ' || last_name from singer where id = :id ";
    private static final String SQL_FIND_BY_FIRSTNAME
            = "select id, first_name, last_name, birth_date from singer where first_name = :firstName";
    private static final String SQL_FIND_ALL
            = "select id, first_name, last_name, birth_date from singer";


    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Singer> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, singerRowMapper);
    }

    @Override
    public List findByFirstName(String firstName) {
        Map params = params(new String[]{"firstName"}, new Object[]{firstName});
        return jdbcTemplate.query(SQL_FIND_BY_FIRSTNAME, params, singerRowMapper);
    }

    @Override
    public String findNameById(Long id) {
        Map params = params(new String[]{"id"}, new Object[]{id});
        return jdbcTemplate.queryForObject(SQL_FIND_NAME_BY_ID,
               params, String.class);
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

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public List<Singer> findAllWithDetail() {
        return jdbcTemplate.query(SQL_FIND_ALL_JOINING_ALBUMS, singerJoiningAlbumExtractor);
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }

    private Map<String, Object> params(String[] keys, Object[] values) {
        Map<String, Object> map = new HashMap<>();
        for(int i=0; i<keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }
}
