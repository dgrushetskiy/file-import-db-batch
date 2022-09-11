package ru.gothmog.importdb.mapper;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import ru.gothmog.importdb.model.inform.DistrictInformData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DistrictInformDataPreparedStatementSetter implements ItemPreparedStatementSetter<DistrictInformData> {
    @Override
    public void setValues(DistrictInformData item, PreparedStatement ps) throws SQLException {
        ps.setString(1,item.getIdLocalityDistrict());
        ps.setString(2,item.getIdKeyInformName());
        ps.setBigDecimal(3,item.getValueDistrict());
        ps.setString(4,item.getReportingPeriod());
        ps.setString(5,item.getDynamics());
    }
}
