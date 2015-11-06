package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;

import static com.epam.brest.course2015.domain.Transaction.TransactionFields.*;
/**
 * Created by user on 06.11.15.
 */
public class TransactionDaoImpl implements TransactionDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${transaction.insert}")
    private String transactionInsert;

    @Value("${transaction.select}")
    private String transactionSelect;

    @Value("${transaction.delete}")
    private String transactionDelete;

    @Value("${transaction.selectbyid}")
    private String tranasctionSelectById;

    public TransactionDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Transaction> getAllTransactions() {
        return jdbcTemplate.query(transactionSelect,new BeanPropertyRowMapper<Transaction>(Transaction.class));
    }

    public Integer addTransaction(Transaction transaction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(transactionInsert,getParametersMap(transaction),keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteTransaction(Integer id_transaction) {
        jdbcTemplate.update(transactionDelete, new Object[]{id_transaction});
    }

    public Transaction getTransactionById(Integer id_transaction) {
        return jdbcTemplate.queryForObject(tranasctionSelectById, new Object[]{id_transaction},
                new BeanPropertyRowMapper<Transaction>(Transaction.class));
    }

    private MapSqlParameterSource getParametersMap(Transaction transaction) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_TRANSACTION.getValue(), transaction.getId_transaction());
        parameterSource.addValue(CHECKNUMBERSENDER.getValue(), transaction.getChecknumbersender());
        parameterSource.addValue(CHECKNUMBERRECIPIENT.getValue(), transaction.getChecknumberrecipient());
        parameterSource.addValue(SUMMA.getValue(), transaction.getSumma());
        parameterSource.addValue(DATE.getValue(), transaction.getDate());
        return parameterSource;
    }
}
