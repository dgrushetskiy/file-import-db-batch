package ru.gothmog.importdb.mapper;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import ru.gothmog.importdb.model.inform.RegionInformData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegionInformDataPreparedStatementSetter implements ItemPreparedStatementSetter<RegionInformData> {
    @Override
    public void setValues(RegionInformData item, PreparedStatement ps) throws SQLException {
        ps.setString(1,item.getIdLocalityRegion());
        ps.setString(2,item.getIdKeyInformName());
        ps.setBigDecimal(3,item.getValueRegion());
        ps.setString(4,item.getReportingPeriod());
        ps.setString(5,item.getDynamics());
    }
}
