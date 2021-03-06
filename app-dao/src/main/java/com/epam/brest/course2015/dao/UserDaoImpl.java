package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Transaction;
import com.epam.brest.course2015.domain.User;
import com.sun.security.jgss.InquireSecContextPermission;
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

    @Value("${transaction.select}")
    private String transactionSelect;

    @Value("${user.insert}")
    private String userInsert;

    @Value("${user.thereItUser}")
    private String userItThere;

    @Value("${user.selectbyid}")
    private String userSelectById;

    @Value("${user.delete}")
    private String userDelete;

    @Value("${transaction.delete}")
    private String transactionDelete;

    @Value("${check.delete}")
    private String checkDelete;

    @Value("${user.selectbylogin}")
    private String userSelectByLogin;

    @Value("${user.count}")
    private String countUser;

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
        List<Transaction> transactions = jdbcTemplate.query(transactionSelect, new Object[]{id_user},
                new BeanPropertyRowMapper<Transaction>(Transaction.class));
        if (transactions.size() != 0) {
            for (int i = 0; i < transactions.size(); i++)
                jdbcTemplate.update(transactionDelete, new Object[]{transactions.get(i).getId_transaction()});
        }

        jdbcTemplate.update(checkDelete, new Object[]{id_user});

        jdbcTemplate.update(userDelete,new Object[]{id_user});
    }

    public User getUserById(Integer id_user) {
        LOGGER.debug("getUserById");
        return jdbcTemplate.queryForObject(userSelectById,new Object[]{id_user},new BeanPropertyRowMapper<User>(User.class));
    }

    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin");
        return jdbcTemplate.queryForObject(userSelectByLogin,new Object[]{login},new BeanPropertyRowMapper<User>(User.class));
    }

    public Integer countUser() {
        LOGGER.debug("countUser");
        return jdbcTemplate.queryForObject(countUser, Integer.class);
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
