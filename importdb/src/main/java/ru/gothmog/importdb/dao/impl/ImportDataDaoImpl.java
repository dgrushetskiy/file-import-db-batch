package ru.gothmog.importdb.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gothmog.importdb.dao.ImportDataDao;
import ru.gothmog.importdb.model.ImportData;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.gothmog.importdb.dao.CloseConnect.finalySQL;

public class ImportDataDaoImpl implements ImportDataDao {

    private static final Logger log = LoggerFactory.getLogger(ImportDataDaoImpl.class);
    @Autowired
    @Qualifier("jdbcTemplateBase")
    public JdbcTemplate jdbcTemplateBase;


    @Override
    public BigDecimal valueData(String idLocality, String idKeyInformName, String reportingPeriod) {
        log.info("get value ImportDao");
        String sql = "select value_all from renov.import_data where id_locality = ? and id_key_inform_name = ? and reporting_period = ?";
        BigDecimal value = BigDecimal.ZERO;
        ImportData importData = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = jdbcTemplateBase.getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idLocality);
            preparedStatement.setString(2,idKeyInformName);
            preparedStatement.setString(3,reportingPeriod);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               value = resultSet.getBigDecimal(1);
            }
        }catch (SQLException ex) {
            log.error("Error when getting all disabled peoples", ex);
        } finally {
            finalySQL(connection, preparedStatement, resultSet, log);
        }
        return value;
    }
}
