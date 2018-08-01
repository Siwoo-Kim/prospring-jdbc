package com.prospring.jdbc.repository.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-30 오후 3:37
 **/


public class UpdateSingerById extends SqlUpdate {
    private static final String UPDATE_SINGER_BY_ID
        = "update singer set first_name = :firstName, last_name = :lastName, birth_date = :birthDate " +
            "where id = :singerId ";

    public UpdateSingerById(DataSource ds) {
        super(ds, UPDATE_SINGER_BY_ID);
        super.setParameters(new SqlParameter("firstName", Types.VARCHAR));
        super.setParameters(new SqlParameter("lastName", Types.VARCHAR));
        super.setParameters(new SqlParameter("birthDate", Types.DATE));
        super.setParameters(new SqlParameter("singerId", Types.INTEGER));
    }
}
