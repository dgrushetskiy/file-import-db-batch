package ru.gothmog.importdb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gothmog.importdb.model.inform.DistrictInformData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistrictInformDataRowMapper implements RowMapper<DistrictInformData> {
    @Override
    public DistrictInformData mapRow(ResultSet rs, int rowNum) throws SQLException {
        DistrictInformData districtInformData = new DistrictInformData();
        districtInformData.setIdLocalityDistrict(rs.getString("id_locality"));
        districtInformData.setIdKeyInformName(rs.getString("id_key_inform_name"));
        districtInformData.setValueDistrict(rs.getBigDecimal("value_all"));
        districtInformData.setReportingPeriod(rs.getString("reporting_period"));
        return districtInformData;
    }
}
