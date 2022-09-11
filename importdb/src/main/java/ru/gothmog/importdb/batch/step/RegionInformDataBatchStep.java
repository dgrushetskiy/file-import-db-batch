package ru.gothmog.importdb.batch.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.importdb.mapper.RegionInformDataPreparedStatementSetter;
import ru.gothmog.importdb.mapper.RegionInformDataRowMapper;
import ru.gothmog.importdb.model.inform.RegionInformData;

import javax.sql.DataSource;

@Configuration
public class RegionInformDataBatchStep {

    @Autowired
    JobRepository jobRepository;

    public ItemReader tableRegionReader(DataSource dataSource){
        String sql = "select id_locality,id_key_inform_name,value_all,reporting_period from renov.import_data where id_locality = '01000000' or id_locality = '02000000' order by id_key_inform_name";
        JdbcCursorItemReader<RegionInformData> jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql(sql);
        jdbcCursorItemReader.setVerifyCursorPosition(false);
        jdbcCursorItemReader.setRowMapper(new RegionInformDataRowMapper());
        return jdbcCursorItemReader;
    }

    public JdbcBatchItemWriter<RegionInformData> jdbcBatchItemWriterRegionInform(DataSource dataSource){
        String sql = "insert into renov.region_inform_data(id_locality_region, id_key_inform_name, value_region, reporting_period, dynamics) VALUES (?,?,?,?,?)";
        JdbcBatchItemWriter<RegionInformData> regionInformDataJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        regionInformDataJdbcBatchItemWriter.setDataSource(dataSource);
        regionInformDataJdbcBatchItemWriter.setSql(sql);
        ItemPreparedStatementSetter<RegionInformData> statementSetter = new RegionInformDataPreparedStatementSetter();
        regionInformDataJdbcBatchItemWriter.setItemPreparedStatementSetter(statementSetter);
        return regionInformDataJdbcBatchItemWriter;
    }
}
