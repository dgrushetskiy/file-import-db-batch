package ru.gothmog.importdb.mapper;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import ru.gothmog.importdb.model.inform.AreaInformData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AreaInformDataPreparedStatementSetter implements ItemPreparedStatementSetter<AreaInformData> {
    @Override
    public void setValues(AreaInformData item, PreparedStatement ps) throws SQLException {
        ps.setString(1,item.getIdLocalityArea());
        ps.setString(2,item.getIdKeyInformName());
        ps.setBigDecimal(3,item.getValueArea());
        ps.setString(4,item.getReportingPeriod());
        ps.setString(5,item.getDynamics());
    }
}
