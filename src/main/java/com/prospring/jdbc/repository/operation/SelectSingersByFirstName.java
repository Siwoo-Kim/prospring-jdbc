package com.prospring.jdbc.repository.operation;

import com.prospring.jdbc.entities.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import static com.prospring.jdbc.repository.operation.MappingSinger.BASIC_OPERATION;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 3:32
 **/


public class SelectSingersByFirstName extends MappingSqlQuery<Singer> {
    private static final String SELECT_SINGERS_BY_FIRSTNAME
            = "select id, first_name, last_name, birth_date from singer " +
            "where first_name = :firstName ";

    public SelectSingersByFirstName(DataSource ds) {
        super(ds, SELECT_SINGERS_BY_FIRSTNAME);
        super.setParameters(new SqlParameter("firstName", Types.VARCHAR));
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BASIC_OPERATION.mapRow(rs);
    }
}
