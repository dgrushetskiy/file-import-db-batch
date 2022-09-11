package ru.gothmog.importdb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gothmog.importdb.model.inform.CityInformData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityInformDataRowMapper implements RowMapper<CityInformData> {
    @Override
    public CityInformData mapRow(ResultSet rs, int rowNum) throws SQLException {
        CityInformData cityInformData = new CityInformData();
        cityInformData.setIdLocalityCity(rs.getString("id_locality"));
        cityInformData.setIdKeyInformName(rs.getString("id_key_inform_name"));
        cityInformData.setValueCity(rs.getBigDecimal("value_all"));
        cityInformData.setReportingPeriod(rs.getString("reporting_period"));
        return cityInformData;
    }
}
