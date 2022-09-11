package ru.gothmog.importdb.mapper;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import ru.gothmog.importdb.model.ImportData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportDataPreparedStatementSetter implements ItemPreparedStatementSetter<ImportData> {
    @Override
    public void setValues(ImportData item, PreparedStatement ps) throws SQLException {
        ps.setString(1,item.getIdLocality());
        ps.setString(2,item.getIdKeyInformName());
        ps.setBigDecimal(3,item.getValueAll());
        ps.setString(4,item.getReportingPeriod());
    }
}
