package ru.gothmog.importdb.mapper;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import ru.gothmog.importdb.model.inform.CityInformData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CityInformDataPreparedStatementSetter implements ItemPreparedStatementSetter<CityInformData> {
    @Override
    public void setValues(CityInformData item, PreparedStatement ps) throws SQLException {
        ps.setString(1,item.getIdLocalityCity());
        ps.setString(2,item.getIdKeyInformName());
        ps.setBigDecimal(3,item.getValueCity());
        ps.setString(4,item.getReportingPeriod());
        ps.setString(5,item.getDynamics());
    }
}
