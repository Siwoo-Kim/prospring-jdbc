package com.prospring.jdbc.repository;

import com.prospring.jdbc.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-25 오전 11:30
 **/


public class JdbcSingerRepository implements SingerRepository {
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerRepository.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            logger.error("Loading Database Driver failed", e);
        }
    }

    private static final String USERNAME = "prospring";
    private static final String PASSWORD = "1234";
    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/MUSICDB", USERNAME, PASSWORD);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private void closeConnection(Connection c) {
        Optional.ofNullable(c)
                .ifPresent(_c -> {
                    try{
                        c.close();
                    }catch (SQLException e) {
                        logger.error("Closing connection to the database failed", e);
                        throw new IllegalStateException();
                    }
                });
    }


    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection c = getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("select * from singer");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Singer singer = new Singer();
                singer.setId(rs.getLong("id"));
                singer.setFirstName(rs.getString("first_name"));
                singer.setLastName(rs.getString("last_name"));
                singer.setBirthDate(rs.getDate("birth_date"));
                result.add(singer);
            }
            ps.close();
        }catch (SQLException e) {
            logger.error("Fetching data failed", e);
        } finally {
            closeConnection(c);
        }
        return result;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
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
        Connection c = getConnection();
        try {
            PreparedStatement ps = c.prepareStatement(
                    "insert into SINGER (first_name, last_name, birth_date) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, singer.getFirstName());
            ps.setString(2, singer.getLastName());
            ps.setDate(3,  new java.sql.Date(singer.getBirthDate().getTime()));
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next()) {
                singer.setId(generatedKeys.getLong(1));
            }
            ps.close();
        }catch (SQLException e) {
            logger.error("Inserting data failed", e);
        }
    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {
        Connection c = getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("delete from singer where id = ?");
            ps.setLong(1,singerId);
            ps.execute();
            ps.close();
        }catch (SQLException e) {
            logger.error("Deleting data failed", e);
        }finally {
            closeConnection(c);
        }
    }

    @Override
    public List<Singer> findAllWithDetail() {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }
}

