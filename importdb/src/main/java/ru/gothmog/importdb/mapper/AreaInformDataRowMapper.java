package ru.gothmog.importdb.mapper;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import ru.gothmog.importdb.model.inform.AreaInformData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaInformDataRowMapper implements RowMapper<AreaInformData> {

    @Override
    public AreaInformData mapRow(ResultSet rs, int rowNum) throws SQLException {
        AreaInformData areaInformData = new  AreaInformData();
        areaInformData.setIdLocalityArea(rs.getString("id_locality"));
        areaInformData.setIdKeyInformName(rs.getString("id_key_inform_name"));
        areaInformData.setValueArea(rs.getBigDecimal("value_all"));
        areaInformData.setReportingPeriod(rs.getString("reporting_period"));
        return areaInformData;
    }
}
