package com.prospring.jdbc.repository.operation;

import com.prospring.jdbc.entities.Singer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 3:29
 **/

@FunctionalInterface
public interface MappingSinger {
    MappingSinger BASIC_OPERATION = (rs) -> {
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setLastName(rs.getString("last_name"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    };

    Singer mapRow(ResultSet rs) throws SQLException;
}
