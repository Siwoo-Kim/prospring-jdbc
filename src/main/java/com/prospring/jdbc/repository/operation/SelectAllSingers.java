package com.prospring.jdbc.repository.operation;

import com.prospring.jdbc.entities.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.prospring.jdbc.repository.operation.MappingSinger.BASIC_OPERATION;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 3:28
 **/


public class SelectAllSingers extends MappingSqlQuery<Singer> {
    private static final String SELECT_ALL_SINGER
            = "select id, first_name, last_name, birth_date from singer ";

    public SelectAllSingers(DataSource dataSource) {
        super(dataSource, SELECT_ALL_SINGER);
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BASIC_OPERATION.mapRow(rs);
    }
}
