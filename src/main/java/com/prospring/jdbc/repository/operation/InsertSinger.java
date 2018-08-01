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
 * @since 2018-07-30 오후 3:41
 **/


public class InsertSinger extends SqlUpdate {
    private static final String INSERT_SINGER
            = "insert into singer (first_name, last_name, birth_date) " +
            "values(:firstName, :lastName, :birthDate) ";

    public InsertSinger(DataSource ds) {
        super(ds, INSERT_SINGER);
        super.setParameters(new SqlParameter("firstName", Types.VARCHAR));
        super.setParameters(new SqlParameter("lastName", Types.VARCHAR));
        super.setParameters(new SqlParameter("birthDate", Types.DATE));
        super.setGeneratedKeysColumnNames(new String[]{"id"});
        super.setReturnGeneratedKeys(true);
    }

}
