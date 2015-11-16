package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;

import static com.epam.brest.course2015.domain.User.UserFields.*;

/**
 * Created by user on 06.11.15.
 */
public class UserDaoImpl implements UserDao {

    private final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${user.select}")
    private String userSelect;

    @Value("${user.insert}")
    private String userInsert;

    @Value("${user.thereItUser}")
    private String userItThere;

    @Value("${user.selectbyid}")
    private String userSelectById;

    @Value("${user.delete}")
    private String userDelete;

    @Value("${user.selectbylogin}")
    private String userSelectByLogin;

    public UserDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers");
        return jdbcTemplate.query(userSelect, new BeanPropertyRowMapper<User>(User.class));
    }

    public Integer addUser(User user) {
        LOGGER.debug("addUser");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(userInsert,getParametersMap(user),keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteUser(Integer id_user) {
        LOGGER.debug("deleteUser");
        jdbcTemplate.update(userDelete,new Object[]{id_user});
    }

//    public void updateUser(User user) {
//
//    }

    public User getUserById(Integer id_user) {
        LOGGER.debug("getUserById");
        return jdbcTemplate.queryForObject(userSelectById,new Object[]{id_user},new BeanPropertyRowMapper<User>(User.class));
    }

    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin");
        return jdbcTemplate.queryForObject(userSelectByLogin,new Object[]{login},new BeanPropertyRowMapper<User>(User.class));
    }

    private MapSqlParameterSource getParametersMap(User user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_USER.getValue(),user.getId_user());
        parameterSource.addValue(LOGIN.getValue(),user.getLogin());
        parameterSource.addValue(PASSWORD.getValue(),user.getPassword());
        parameterSource.addValue(FIRSTNAME.getValue(),user.getFirstname());
        parameterSource.addValue(LASTNAME.getValue(),user.getLastname());
        return parameterSource;
    }

}
