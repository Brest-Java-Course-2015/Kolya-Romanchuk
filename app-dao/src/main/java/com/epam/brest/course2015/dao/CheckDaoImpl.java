package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.brest.course2015.domain.Check.CheckField.*;

/**
 * Created by user on 06.11.15.
 */
public class CheckDaoImpl implements CheckDao {

    private final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${check.select}")
    private String checkSelect;

    @Value("${check.insert}")
    private String checkInsert;

    @Value("${check.update}")
    private String checkUpdate;

    @Value("${check.selectbyid}")
    private String checkSelectById;

    @Value("${check.delete}")
    private String checkDelete;

    @Value("${check.selectbynumber}")
    private String checkSelectByNumber;

    public CheckDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Check> getAllChecks() {
        LOGGER.debug("getAllChecks");
        return jdbcTemplate.query(checkSelect,new BeanPropertyRowMapper<Check>(Check.class));
    }

    public Integer addCheck(Check check) {
        LOGGER.debug("addCheck");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(checkInsert, getParametersMap(check),keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteCheck(Integer id_check) {
        LOGGER.debug("deleteCheck");
        jdbcTemplate.update(checkDelete, new Object[]{id_check});
    }

    public void updateCheck(Check check) {
        LOGGER.debug("updateCheck");
        jdbcTemplate.update(checkUpdate, new Object[]{check.getSumma()});
    }

    public Check getCheckById(Integer id_check) {
        LOGGER.debug("getCheckById");
        return jdbcTemplate.queryForObject(checkSelectById, new Object[]{id_check},
                new BeanPropertyRowMapper<Check>(Check.class));
    }

    public Check getCheckByCheckNumder(Integer cheknumber) {
        LOGGER.debug("getCheckByCheckNumder");
        return jdbcTemplate.queryForObject(checkSelectByNumber, new Object[]{cheknumber},
                new BeanPropertyRowMapper<Check>(Check.class));
    }


    private MapSqlParameterSource getParametersMap(Check check) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_CHEK.getValue(),check.getId_check());
        parameterSource.addValue(CHECKNUMBER.getValue(),check.getChecknumber() );
        parameterSource.addValue(SUMMA.getValue(),check.getSumma());
        parameterSource.addValue(ID_USER.getValue(),check.getId_user());
        return parameterSource;
    }
}
