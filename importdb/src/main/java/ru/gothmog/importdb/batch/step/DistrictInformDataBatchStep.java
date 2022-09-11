package ru.gothmog.importdb.batch.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.importdb.mapper.DistrictInformDataPreparedStatementSetter;
import ru.gothmog.importdb.mapper.DistrictInformDataRowMapper;
import ru.gothmog.importdb.model.inform.DistrictInformData;

import javax.sql.DataSource;

@Configuration
public class DistrictInformDataBatchStep {

    @Autowired
    JobRepository jobRepository;

    public ItemReader tableRegionDistrictReader(DataSource dataSource){
        String sql = "select id_locality,id_key_inform_name,value_all,reporting_period from renov.import_data\n" +
                "where\n" +
                "  id_locality = '01010000' or\n" +
                "  id_locality = '01020000' or\n" +
                "  id_locality = '01030000' or\n" +
                "  id_locality = '01040000' or\n" +
                "  id_locality = '01050000' or\n" +
                "  id_locality = '01060000' or\n" +
                "  id_locality = '01070000' or\n" +
                "  id_locality = '01080000' or\n" +
                "  id_locality = '01090000' or\n" +
                "  id_locality = '01100000' or\n" +
                "  id_locality = '02010000' or\n" +
                "  id_locality = '02020000'\n" +
                "order by id_key_inform_name";
        JdbcCursorItemReader<DistrictInformData> jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql(sql);
        jdbcCursorItemReader.setVerifyCursorPosition(false);
        jdbcCursorItemReader.setRowMapper(new DistrictInformDataRowMapper());
        return jdbcCursorItemReader;
    }

    public JdbcBatchItemWriter<DistrictInformData> jdbcBatchItemWriterDistrictInform(DataSource dataSource){
        String sql = "insert into renov.district_inform_data(id_locality_district, id_key_inform_name, value_district, reporting_period, dynamics) VALUES (?,?,?,?,?)";
        JdbcBatchItemWriter<DistrictInformData> districtInformDataJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        districtInformDataJdbcBatchItemWriter.setDataSource(dataSource);
        districtInformDataJdbcBatchItemWriter.setSql(sql);
        ItemPreparedStatementSetter<DistrictInformData> statementSetter = new DistrictInformDataPreparedStatementSetter();
        districtInformDataJdbcBatchItemWriter.setItemPreparedStatementSetter(statementSetter);
        return districtInformDataJdbcBatchItemWriter;
    }
}
