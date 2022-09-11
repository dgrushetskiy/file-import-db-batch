package ru.gothmog.importdb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gothmog.importdb.model.inform.RegionInformData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionInformDataRowMapper implements RowMapper<RegionInformData> {
    @Override
    public RegionInformData mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegionInformData regionInformData = new RegionInformData();
        regionInformData.setIdLocalityRegion(rs.getString("id_locality"));
        regionInformData.setIdKeyInformName(rs.getString("id_key_inform_name"));
        regionInformData.setValueRegion(rs.getBigDecimal("value_all"));
        regionInformData.setReportingPeriod(rs.getString("reporting_period"));
        return regionInformData;
    }
}
