package com.linkfeeling.platform.repo;

import com.linkfeeling.platform.bean.jpa.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SimpleJDBCRepository {
    private static final Logger logger = LoggerFactory.getLogger(SimpleJDBCRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void testConnect(){
        jdbcTemplate.query("select * from system_user", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                logger.info("name->"+rs.getString("name"));
            }
        });


    }

    public List<SystemUser> getSystemUserList(){
        return jdbcTemplate.query("select * from system_user", new RowMapper<SystemUser>() {
            @Override
            public SystemUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SystemUser(rs.getLong("id"),rs.getString("name"),"******");
            }
        });
    }
}
