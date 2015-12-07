package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Check;
import com.epam.brest.course2015.domain.Transaction;
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
import java.util.Date;
import java.util.List;

import static com.epam.brest.course2015.domain.Transaction.TransactionFields.*;
//import static com.epam.brest.course2015.domain.Check.CheckField.*;
/**
 * Created by user on 06.11.15.
 */
public class TransactionDaoImpl implements TransactionDao {

    private final Logger LOGGER = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${check.update}")
    private String checkUpdate;

    @Value("${check.selectbynumber}")
    private String checkSelectByNumber;

    @Value("${transaction.filter}")
    private String tranasctionFilter;

    @Value("${transaction.insert}")
    private String transactionInsert;

    @Value("${transaction.select}")
    private String transactionSelect;

    @Value("${transaction.delete}")
    private String transactionDelete;

    @Value("${transaction.selectbyid}")
    private String tranasctionSelectById;

    @Value("${check.summ}")
    private String checkSumm;

    @Value("${check.min}")
    private String checkMin;

    @Value("${transaction.summ}")
    private String transactionTotalSumm;

    @Value("${transaction.summfilter}")
    private String transactionFilterSumm;

    public TransactionDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Transaction> getAllTransactions(Integer id_user) {
        LOGGER.debug("getAllTransactions");
        return jdbcTemplate.query(transactionSelect,new Object[]{id_user},new BeanPropertyRowMapper<Transaction>(Transaction.class));
    }

    public Integer totalFilterSumm(Integer id_user, Date date_from, Date date_before) {
        LOGGER.debug("totalFilterSumm");
        return jdbcTemplate.queryForObject(transactionFilterSumm, new Object[]{date_from, date_before, id_user}, Integer.class);
    }

    public Integer totalSumm(Integer id_user){
        LOGGER.debug("totalSumm");
        return jdbcTemplate.queryForObject(transactionTotalSumm, new Object[]{id_user}, Integer.class);
    }

    public Integer addTransaction(Transaction transaction) {
        LOGGER.debug("addTransaction");
        Integer summ_recipient = jdbcTemplate.queryForObject(checkSumm,new Object[]{transaction.getSumma(),
                transaction.getChecknumberrecipient()}, Integer.class);
        Integer summ_sender = jdbcTemplate.queryForObject(checkMin,new Object[]{transaction.getSumma(),
                transaction.getChecknumbersender()}, Integer.class);

        jdbcTemplate.update(checkUpdate, new Object[]{summ_sender, transaction.getChecknumbersender()});
        jdbcTemplate.update(checkUpdate, new Object[]{summ_recipient, transaction.getChecknumberrecipient()});
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(transactionInsert,getParametersMapTransaction(transaction),keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteTransaction(Integer id_transaction) {
        LOGGER.debug("deleteTransaction");
        jdbcTemplate.update(transactionDelete, new Object[]{id_transaction});
    }

    public Transaction getTransactionById(Integer id_transaction) {
        LOGGER.debug("getTransactionById");
        return jdbcTemplate.queryForObject(tranasctionSelectById, new Object[]{id_transaction},
                new BeanPropertyRowMapper<Transaction>(Transaction.class));
    }

    public List<Transaction> getFiltertransactions(Integer id_user,Date date_from, Date date_before) {
        LOGGER.debug("getFilterTransactions");
        return jdbcTemplate.query(tranasctionFilter, new Object[]{date_from, date_before, id_user},new BeanPropertyRowMapper<Transaction>(Transaction.class));
    }

    private MapSqlParameterSource getParametersMapTransaction(Transaction transaction) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_TRANSACTION.getValue(), transaction.getId_transaction());
        parameterSource.addValue(CHECKNUMBERSENDER.getValue(), transaction.getChecknumbersender());
        parameterSource.addValue(CHECKNUMBERRECIPIENT.getValue(), transaction.getChecknumberrecipient());
        parameterSource.addValue(SUMMA.getValue(), transaction.getSumma());
        parameterSource.addValue(DATE.getValue(), transaction.getDate());
        parameterSource.addValue(ID_CHECK.getValue(), transaction.getId_check());
        return parameterSource;
    }
}
