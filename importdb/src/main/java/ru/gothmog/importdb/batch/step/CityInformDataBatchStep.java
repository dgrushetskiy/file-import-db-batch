package ru.gothmog.importdb.batch.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.importdb.mapper.CityInformDataPreparedStatementSetter;
import ru.gothmog.importdb.mapper.CityInformDataRowMapper;
import ru.gothmog.importdb.model.inform.CityInformData;

import javax.sql.DataSource;

@Configuration
public class CityInformDataBatchStep {

    @Autowired
    JobRepository jobRepository;

    public ItemReader tableCityReader(DataSource dataSource){
        String sql = "select id_locality,id_key_inform_name,value_all,reporting_period from renov.import_data where id_locality = '00000000' order by id_key_inform_name";
        JdbcCursorItemReader<CityInformData> jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql(sql);
        jdbcCursorItemReader.setVerifyCursorPosition(false);
        jdbcCursorItemReader.setRowMapper(new CityInformDataRowMapper());
        return jdbcCursorItemReader;
    }

    public JdbcBatchItemWriter<CityInformData> jdbcBatchItemWriterCityInform(DataSource dataSource){
        String sql = "insert into renov.city_inform_data(id_locality_city, id_key_inform_name, value_city, reporting_period, dynamics) values (?,?,?,?,?)";
        JdbcBatchItemWriter<CityInformData> cityInformDataJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        cityInformDataJdbcBatchItemWriter.setDataSource(dataSource);
        cityInformDataJdbcBatchItemWriter.setSql(sql);
        ItemPreparedStatementSetter<CityInformData> statementSetter = new CityInformDataPreparedStatementSetter();
        cityInformDataJdbcBatchItemWriter.setItemPreparedStatementSetter(statementSetter);
        return cityInformDataJdbcBatchItemWriter;
    }
}
